package com.nexusmarket.persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.nexusmarket.model.Product;
import com.nexusmarket.model.User;
import org.bson.Document;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Dual persistence adapter for SQL Server and MongoDB.
 * Credentials are read from environment variables and are never stored in source code.
 */
public class DatabaseService implements AutoCloseable {

    private final String sqlUrl = env("NEXUS_SQL_URL",
            "jdbc:sqlserver://localhost:1433;databaseName=NexusMarket;encrypt=true;trustServerCertificate=true");
    private final String sqlUser = env("NEXUS_SQL_USER", "sa");
    private final String sqlPassword = env("NEXUS_SQL_PASSWORD", "");
    private final String mongoUri = env("NEXUS_MONGO_URI", "mongodb://localhost:27017");
    private final String mongoDatabase = env("NEXUS_MONGO_DATABASE", "NexusMarket");

    private static String env(String key, String fallback) {
        String value = System.getenv(key);
        return value == null || value.trim().isEmpty() ? fallback : value.trim();
    }

    private Connection sqlConnection() throws Exception {
        if (sqlPassword.isEmpty()) {
            throw new IllegalStateException("Set NEXUS_SQL_PASSWORD before connecting to SQL Server.");
        }
        return DriverManager.getConnection(sqlUrl, sqlUser, sqlPassword);
    }

    private MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }

    /** Creates the SQL tables and MongoDB indexes if they do not exist. */
    public void initializeSqlSchema() throws Exception {
        try (Connection connection = sqlConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("IF OBJECT_ID('dbo.NM_Users', 'U') IS NULL " +
                    "CREATE TABLE dbo.NM_Users (id INT NOT NULL PRIMARY KEY, full_name NVARCHAR(160) NOT NULL, " +
                    "email NVARCHAR(254) NOT NULL UNIQUE, role NVARCHAR(40) NOT NULL, status NVARCHAR(40) NOT NULL, " +
                    "main_address NVARCHAR(500) NULL)");
            statement.executeUpdate("IF COL_LENGTH('dbo.NM_Users', 'main_address') IS NULL " +
                    "ALTER TABLE dbo.NM_Users ADD main_address NVARCHAR(500) NULL");
            statement.executeUpdate("IF OBJECT_ID('dbo.NM_Products', 'U') IS NULL " +
                    "CREATE TABLE dbo.NM_Products (id INT NOT NULL PRIMARY KEY, name NVARCHAR(160) NOT NULL, " +
                    "product_type NVARCHAR(80) NOT NULL, status NVARCHAR(40) NOT NULL)");
        }
    }

    public String testSqlServer() {
        try (Connection ignored = sqlConnection()) {
            initializeSqlSchema();
            return "SQL Server: connected. Tables are ready.";
        } catch (Exception ex) {
            return "SQL Server: connection failed - " + ex.getMessage();
        }
    }

    public String testMongoDb() {
        try (MongoClient client = mongoClient()) {
            client.getDatabase(mongoDatabase).runCommand(new Document("ping", 1));
            MongoDatabase db = client.getDatabase(mongoDatabase);
            db.getCollection("users").createIndex(new Document("id", 1), new com.mongodb.client.model.IndexOptions().unique(true));
            db.getCollection("users").createIndex(new Document("email", 1), new com.mongodb.client.model.IndexOptions().unique(true));
            db.getCollection("products").createIndex(new Document("id", 1), new com.mongodb.client.model.IndexOptions().unique(true));
            return "MongoDB: connected. Collections and indexes are ready.";
        } catch (Exception ex) {
            return "MongoDB: connection failed - " + ex.getMessage();
        }
    }

    /** Inserts or synchronizes a user in both databases (safe to retry). */
    public void saveUser(User user) throws Exception {
        String address = user instanceof com.nexusmarket.model.Buyer
                ? ((com.nexusmarket.model.Buyer) user).getMainAddress() : null;
        try (Connection connection = sqlConnection();
             PreparedStatement sql = connection.prepareStatement(
                     "MERGE dbo.NM_Users AS target " +
                     "USING (SELECT ? AS id, ? AS full_name, ? AS email, ? AS role, ? AS status, ? AS main_address) AS source " +
                     "ON target.id = source.id " +
                     "WHEN MATCHED THEN UPDATE SET full_name=source.full_name, email=source.email, role=source.role, status=source.status, main_address=source.main_address " +
                     "WHEN NOT MATCHED THEN INSERT (id, full_name, email, role, status, main_address) " +
                     "VALUES (source.id, source.full_name, source.email, source.role, source.status, source.main_address);")) {
            sql.setInt(1, user.getId());
            sql.setString(2, user.getFullName());
            sql.setString(3, user.getEmail());
            sql.setString(4, user.getRole().name());
            sql.setString(5, user.getStatus().name());
            sql.setString(6, address);
            sql.executeUpdate();
        }
        try (MongoClient client = mongoClient()) {
            collection(client, "users").replaceOne(Filters.eq("id", user.getId()),
                    userDocument(user), new com.mongodb.client.model.ReplaceOptions().upsert(true));
        }
    }

    public void updateUser(User user) throws Exception {
        saveUser(user);
    }

    /** Inserts or synchronizes a product in both databases (safe to retry). */
    public void saveProduct(Product product) throws Exception {
        try (Connection connection = sqlConnection();
             PreparedStatement sql = connection.prepareStatement(
                     "MERGE dbo.NM_Products AS target " +
                     "USING (SELECT ? AS id, ? AS name, ? AS product_type, ? AS status) AS source " +
                     "ON target.id = source.id " +
                     "WHEN MATCHED THEN UPDATE SET name=source.name, product_type=source.product_type, status=source.status " +
                     "WHEN NOT MATCHED THEN INSERT (id, name, product_type, status) " +
                     "VALUES (source.id, source.name, source.product_type, source.status);")) {
            bindProduct(sql, product);
            sql.executeUpdate();
        }
        try (MongoClient client = mongoClient()) {
            collection(client, "products").replaceOne(Filters.eq("id", product.getId()),
                    productDocument(product), new com.mongodb.client.model.ReplaceOptions().upsert(true));
        }
    }

    public void updateProduct(Product product) throws Exception {
        saveProduct(product);
    }

    private static MongoCollection<Document> collection(MongoClient client, String name) {
        return client.getDatabase(env("NEXUS_MONGO_DATABASE", "NexusMarket")).getCollection(name);
    }

    private static Document userDocument(User user) {
        return new Document("id", user.getId())
                .append("fullName", user.getFullName())
                .append("email", user.getEmail())
                .append("role", user.getRole().name())
                .append("status", user.getStatus().name())
                .append("mainAddress", user instanceof com.nexusmarket.model.Buyer
                        ? ((com.nexusmarket.model.Buyer) user).getMainAddress() : null);
    }

    private static Document productDocument(Product product) {
        return new Document("id", product.getId())
                .append("name", product.getName())
                .append("productType", product.getProductType())
                .append("status", product.getStatus().name());
    }

    private static void bindProduct(PreparedStatement sql, Product product) throws Exception {
        sql.setInt(1, product.getId());
        sql.setString(2, product.getName());
        sql.setString(3, product.getProductType());
        sql.setString(4, product.getStatus().name());
    }

    @Override
    public void close() {
        // Connections and Mongo clients are opened per operation and closed immediately.
    }
}

# NexusMarket Database Setup

The original project folders and Java package structure are preserved. The database integration is added under `src/com/nexusmarket/persistence`.

## Supported databases

- Microsoft SQL Server stores users and products in `dbo.NM_Users` and `dbo.NM_Products`.
- MongoDB stores users in the `users` collection and products in the `products` collection.
- Buyer addresses are stored as `main_address` / `mainAddress`. Passwords are deliberately excluded from both databases.
- The application writes user and product records to both databases. Passwords and password hashes are not sent to either database by this adapter.

## 1. Start both database servers

If Docker Desktop is installed, from the project root run:

```bash
docker compose up -d
```

This starts SQL Server 2022 on port `1433` and MongoDB 7 on port `27017`, with persistent Docker volumes. The development SQL Server password defaults to `NexusDev_2026!ChangeMe`; override it with `NEXUS_SQL_PASSWORD` in your shell or a local `.env` file used by Docker Compose. Do not use the development password outside a local exercise environment.

Create the SQL database and tables by opening `database/sqlserver-schema.sql` in SQL Server Management Studio and executing it against the SQL Server instance. The app can create missing tables after connecting, but the database itself must exist first.

Set these environment variables in the terminal where you will start the Java application:

- `NEXUS_SQL_URL`
- `NEXUS_SQL_USER`
- `NEXUS_SQL_PASSWORD`

Example URL:

`jdbc:sqlserver://localhost:1433;databaseName=NexusMarket;encrypt=true;trustServerCertificate=true`

## 2. Configure MongoDB

Start MongoDB locally or provide a MongoDB connection string. Set:

- `NEXUS_MONGO_URI` (example: `mongodb://localhost:27017`)
- `NEXUS_MONGO_DATABASE` (default: `NexusMarket`)

The application creates unique indexes on `id` and `email` in `users`, and on `id` in `products`. You can also run `database/mongodb-setup.js` using `mongosh`.

Example in Windows PowerShell (replace the password if you changed the Compose setting):

```powershell
$env:NEXUS_SQL_URL="jdbc:sqlserver://localhost:1433;databaseName=NexusMarket;encrypt=true;trustServerCertificate=true"
$env:NEXUS_SQL_USER="sa"
$env:NEXUS_SQL_PASSWORD="NexusDev_2026!ChangeMe"
$env:NEXUS_MONGO_URI="mongodb://localhost:27017"
$env:NEXUS_MONGO_DATABASE="NexusMarket"
```

## 3. Build and run

Install JDK 11 or later and Apache Maven. From the project root run:

`mvn clean compile exec:java`

On Windows, `run.bat` runs the same command.

Maven downloads the Microsoft SQL Server JDBC driver and MongoDB Java driver.

## 4. Use the application

1. Run `database/sqlserver-schema.sql` once in SQL Server Management Studio to create the database and tables. The app creates missing tables and migrates the buyer-address column after it can connect.
2. Start MongoDB and optionally run `database/mongodb-setup.js` with `mongosh`; the app also creates the unique indexes when testing the connection.
3. Configure the environment variables and launch the application.
4. Select **Database connections** to test both connections.
5. Public registration, administrator user creation, product creation, profile changes, user status/role changes and product changes are synchronized automatically. The **Save to databases** and **Update in databases** buttons are manual retry/synchronization actions; both use upsert semantics.
6. The demo administrator is created in memory at startup. Select it in the Users tab and use **Save to databases** to persist it.

## Important notes

- Configure credentials using environment variables. Do not commit real passwords or connection strings to source control.
- SQL Server and MongoDB must both be reachable for a dual-database save/update to complete. Each database operation is independent; if one database succeeds and the other fails, a partial write can remain. Retry the synchronization action; upsert semantics make this safe in normal retry scenarios.
- This integration currently persists the records managed by the Swing UI: users and products. Other domain classes (orders, carts, inventory, invoices, shipments, returns, refunds and warehouses) exist in the model/services but are not yet exposed as complete UI workflows and are not persisted by this adapter. This is a documented next step, not a claim that every domain object is already stored.
- The demo administrator is created in memory at startup. Use **Save to databases** on the Users tab to synchronize it to both databases.

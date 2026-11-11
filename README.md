# NexusMarket

Academic project based on the Nexus Market document (classes, services and system behaviour).

## Structure
- `src/com/nexusmarket/model`   – domain classes (User, Buyer, Seller, Administrator, LogisticsOperator, Product, ...)
- `src/com/nexusmarket/service` – services (one per class, plus `AdministratorService` shared by Administrator and Supervisor)
- `src/com/nexusmarket/enums`   – enums (EmployeeType, UserStatus, ProductStatus, DocumentType)
- `src/com/nexusmarket/ui`      – Swing front: login, registration and one panel per role
- `src/com/nexusmarket/Main.java` – console demo of the full flow

Administrator and Supervisor are the same class/service; they differ only by the `role` attribute.

## Build and run
```
mkdir -p bin
javac -d bin $(find src -name '*.java')
java -cp bin com.nexusmarket.Main
```

## Graphical interface (login)
```
java -cp bin com.nexusmarket.ui.NexusApp
```
Demo administrator: ID `1`, password `Admin#2026`. The public registration form creates the account and attempts to save it in SQL Server and MongoDB. Administrators can also create and synchronize users from the Users tab.

Rules: user IDs and emails are unique, product IDs are auto-incremented, passwords need 8 to 64 characters with uppercase, lowercase, a number and a special character (hashed with PBKDF2 in application memory), and an account is suspended after 3 failed login attempts. Credentials are not persisted yet, so demo/user authentication does not survive an application restart (an administrator can reactivate it).


## Database integration (SQL Server + MongoDB)

The application includes dual database persistence for users and products. Public registration, administrator-created users, product creation, profile changes, status/role changes and product changes are synchronized to SQL Server and MongoDB. The persistence adapter uses retry-safe upserts for synchronization.

See [DATABASE_SETUP.md](DATABASE_SETUP.md) for setup instructions, schema scripts, connection settings, and usage details.

Build and run with JDK 11+ and Maven:

```
mvn clean compile exec:java
```

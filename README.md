# NexusMarket

Academic project based on the Nexus Market document (classes, services and system behaviour).

## Structure (Hexagonal Architecture)
```text
src/main/java/com/nexusmarket/
├── Main.java
├── domain/
│   ├── model/                 – entities, enums and value objects
│   ├── ports/in/              – use case / input ports
│   ├── ports/out/             – persistence and logistics output ports
│   └── service/               – pure business logic implementing input ports
└── infrastructure/
    └── adapters/
        ├── persistence/       – JDBC/MongoDB database adapters
        └── ui/                – Swing login, registration and role panels
```

Administrator and Supervisor are the same class/service; they differ only by the `role` attribute.

## Build and run (JDK 17 + Maven)
```
mvn clean compile
mvn exec:java
```

Console demo of the full flow:
```
mvn exec:java -Dexec.mainClass=com.nexusmarket.Main
```

Graphical interface (login):
```
mvn exec:java -Dexec.mainClass=com.nexusmarket.infrastructure.adapters.ui.NexusApp
```
Demo administrator: ID `1`, password `Admin#2026`. The public registration form creates the account and attempts to save it in SQL Server and MongoDB. Administrators can also create and synchronize users from the Users tab.

Rules: user IDs and emails are unique, product IDs are auto-incremented, passwords need 8 to 64 characters with uppercase, lowercase, a number and a special character (hashed with PBKDF2 in application memory), and an account is suspended after 3 failed login attempts. Credentials are not persisted yet, so demo/user authentication does not survive an application restart (an administrator can reactivate it).


## Database integration (SQL Server + MongoDB)

The application includes dual database persistence for users and products. Public registration, administrator-created users, product creation, profile changes, status/role changes and product changes are synchronized to SQL Server and MongoDB. The persistence adapter uses retry-safe upserts for synchronization.

See [DATABASE_SETUP.md](DATABASE_SETUP.md) for setup instructions, schema scripts, connection settings, and usage details.

Build and run with JDK 17+ and Maven:

```
mvn clean compile exec:java
```

# Capa — Infraestructura

La infraestructura de persistencia concreta usa Microsoft SQL Server mediante JDBC y MongoDB mediante `mongodb-driver-sync`. La configuración se inyecta por variables de entorno (`NEXUS_SQL_*` y `NEXUS_MONGO_*`) y se documenta en `DATABASE_SETUP.md`. `DatabaseService` administra clientes/conexiones por operación y prepara tablas/índices al probar las conexiones.

La integración dual cubre actualmente usuarios y productos. No hay transacción distribuida entre los motores; los upserts permiten reintentar. La autenticación sigue siendo en memoria y no se guardan contraseñas ni hashes en las bases de datos.

# Adaptadores de persistencia — implementación actual

## Objetivo
La aplicación Swing conecta el dominio actual con dos motores de almacenamiento: Microsoft SQL Server (relacional) y MongoDB (documental). La integración concreta se encuentra en `src/main/java/com/nexusmarket/infrastructure/adapters/persistence/DatabaseService.java`; `infrastructure/adapters/ui/AppContext.java` comparte una instancia entre las ventanas.

## Responsabilidad por motor

| Motor | Base / esquema | Colecciones o tablas | Datos actuales |
|---|---|---|---|
| SQL Server | `NexusMarket` | `dbo.NM_Users`, `dbo.NM_Products` | Usuarios, correo, rol, estado, dirección de comprador; productos, tipo y estado |
| MongoDB | `NexusMarket` | `users`, `products` | Documentos equivalentes a los registros relacionales |

Los identificadores son únicos en ambas bases. SQL Server impone clave primaria para `id` y unicidad para `email`; MongoDB crea índices únicos sobre `id`. La dirección de comprador es opcional.

## Flujo de escritura
1. Los casos de uso actuales actualizan el modelo en memoria (`UserService`, `ProductService`).
2. La interfaz llama a `DatabaseService` cuando se registra un usuario, se crea un producto o se actualizan perfiles, roles, estados o productos.
3. El adapter convierte el modelo a parámetros JDBC o `org.bson.Document`.
4. Las escrituras usan `MERGE` en SQL Server y `replaceOne(..., upsert=true)` en MongoDB: una repetición inserta si no existe o actualiza si ya existe.
5. Cada conexión/cliente se cierra al terminar la operación.

## Configuración
Las credenciales y URI se leen de variables de entorno: `NEXUS_SQL_URL`, `NEXUS_SQL_USER`, `NEXUS_SQL_PASSWORD`, `NEXUS_MONGO_URI` y `NEXUS_MONGO_DATABASE`. No se deben almacenar credenciales reales en el repositorio. Ver `DATABASE_SETUP.md`, `.env.example` y los scripts de `database/`.

## Seguridad y consistencia
- Las contraseñas y sus hashes no se envían a las bases de datos. La autenticación y las credenciales siguen siendo de sesión/memoria en esta versión; por tanto, esta integración no resuelve todavía la persistencia de credenciales ni el inicio de sesión tras reiniciar la aplicación.
- SQL Server y MongoDB no comparten una transacción distribuida. Si una escritura termina correctamente en un motor y falla en el otro, puede existir una diferencia temporal; los upserts permiten reintentar la sincronización.
- La conexión de SQL Server debe apuntar a una base `NexusMarket` ya creada. `database/sqlserver-schema.sql` crea la base y las tablas. La aplicación crea tablas faltantes y agrega `main_address` para instalaciones anteriores.

## Alcance pendiente
La interfaz Swing implementa flujos editables completos principalmente para usuarios y productos; por eso son las entidades persistidas hoy. Aunque existen modelos/servicios de pedidos, carritos, inventario, facturas, envíos, devoluciones, reembolsos y bodegas, aún no tienen flujos completos conectados a este adapter. Para persistir el dominio completo se deben definir sus esquemas, relaciones, repositorios y operaciones de lectura/escritura.

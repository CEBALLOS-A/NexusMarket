# Capa — Adapters de persistencia

La implementación actual se concentra en `com.nexusmarket.persistence.DatabaseService`, compartido por `AppContext`. El adapter usa JDBC para SQL Server y el driver síncrono oficial de MongoDB.

- SQL Server: base `NexusMarket`, tablas `dbo.NM_Users` y `dbo.NM_Products`.
- MongoDB: base `NexusMarket`, colecciones `users` y `products`.
- Los registros de usuarios incluyen ID, nombre, correo, rol, estado y dirección de comprador cuando aplica. Los productos incluyen ID, nombre, tipo y estado.
- Los índices/constraints únicos protegen identificadores; SQL Server también protege el correo.
- Las operaciones de sincronización son upserts para facilitar reintentos.

La integración actual cubre usuarios y productos que la interfaz Swing permite gestionar. Los demás modelos de dominio todavía no están conectados a persistencia. No existe una transacción distribuida entre ambos motores, por lo que un fallo parcial requiere reintentar la sincronización. Las contraseñas no se persisten y la autenticación continúa siendo temporal en memoria.

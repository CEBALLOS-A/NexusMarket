# Output Ports — Interfaces de salida

Los puertos de salida aíslan el dominio de los mecanismos de persistencia e integración.

## Repositorios propuestos
- `UserRepositoryPort`: buscar por identificador/correo, guardar y consultar usuarios.
- `BuyerRepositoryPort`: guardar y consultar compradores.
- `SellerRepositoryPort`: guardar y consultar vendedores.
- `WarehouseRepositoryPort`: guardar y consultar bodegas.
- `ProductRepositoryPort`: guardar, consultar y listar productos por vendedor/estado.
- `InventoryRepositoryPort`: consultar existencias por producto y bodega; registrar movimientos.
- `CartRepositoryPort`: guardar y recuperar carritos.
- `OrderRepositoryPort`: guardar y consultar pedidos y estados.
- `InvoiceRepositoryPort`: guardar y consultar facturación.
- `ShipmentRepositoryPort`: guardar y consultar envíos.
- `ReturnRepositoryPort`: guardar y consultar solicitudes de devolución.
- `RefundRepositoryPort`: registrar y consultar reembolsos.
- `AdministrativeReportQueryPort`: consultar datos consolidados.

## Reglas
Los contratos reciben y devuelven modelos de dominio, no entidades JPA ni documentos MongoDB. En la implementación Swing actual, `DatabaseService` actúa como adaptador concreto JDBC/MongoDB para usuarios y productos; los puertos formales y la persistencia de los demás agregados quedan como evolución arquitectónica pendiente.

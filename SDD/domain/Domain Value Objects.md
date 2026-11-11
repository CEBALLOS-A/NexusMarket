# Domain Value Objects — NexusMarket

## Enumeraciones confirmadas o derivadas directamente de la especificación

### UserRole
`BUYER`, `SELLER`, `LOGISTIC_OPERATOR`, `ADMINISTRATOR`, `SUPERVISOR`.

### UserStatus
La fuente menciona estados como `ACTIVE` y `BLOCKED`; catálogo completo por definir.

### BuyerCommercialStatus
Estado comercial del comprador. Valores posibles no especificados.

### WarehouseType
`MARKETPLACE`, `SELLER`.

### ProductType
`PHYSICAL`, `DIGITAL`.

### ProductStatus
`PUBLISHED`, `SUSPENDED`, `DISCONTINUED`.

### InventoryMovementType
`INFLOW`, `RESERVATION`, `SALE`, `ADJUSTMENT`, `RETURN`.

### OrderStatus
`CART`, `PENDING_PAYMENT`, `PAID`, `SHIPPED`, `DELIVERED` (finalizado).

### InventoryCondition
Se reconoce la condición `DAMAGED` por la validación de reserva. El resto del catálogo está por definir.

## Objetos de valor propuestos
- `EmailAddress`: correo con formato válido y unicidad aplicada en el repositorio.
- `UserIdentifier`: identificador único del usuario.
- `Address`: dirección de entrega; estructura detallada pendiente.
- `Quantity`: cantidad no negativa y validada en cada movimiento.
- `Money`: importe y moneda para operaciones comerciales; moneda, precisión e impuestos pendientes de definición.

Los objetos de valor anteriores son decisiones de diseño recomendadas. La especificación no detalla su implementación ni todos sus formatos.

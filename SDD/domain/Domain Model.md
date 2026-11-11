# Domain Model — NexusMarket

## Introducción
El modelo representa los conceptos explícitos de la especificación funcional. Los atributos no descritos por la fuente se marcan como **por definir** y no deben asumirse como requisitos confirmados.

## Entidades

### User
Representa a toda persona autorizada para interactuar con el Marketplace.
- `id`: identificador único.
- `fullName`: nombre completo.
- `email`: correo único.
- `role`: rol único.
- `status`: estado operativo (por ejemplo, activo o bloqueado).
Relaciones: un usuario participa con un único rol; puede especializarse funcionalmente como comprador, vendedor, operador logístico, administrador o supervisor.

### Buyer
Información comercial específica del comprador.
- `userId`
- `primaryAddress`
- `additionalAddresses` (opcional)
- `commercialStatus`
Relaciones: asociado a un usuario; participa en carritos y pedidos. No administra otros compradores ni inventarios.

### Seller
Proveedor que comercializa productos.
- `userId`
- datos administrativos adicionales: por definir.
Regla: su incorporación la realiza el Administrador, no se auto-registra. Un vendedor administra sus productos.

### Warehouse
Espacio físico de almacenamiento.
- `id`
- `warehouseType`: bodega del Marketplace o de vendedor.
- demás datos de ubicación y estado: por definir.
Relaciones: contiene existencias de productos mediante Inventory.

### Product
Bien físico o digital ofrecido en el catálogo.
- `id`
- `sellerId`
- `productType`: físico o digital.
- `variants`: diferencias como color, talla o modelo.
- `status`: publicado, suspendido o descontinuado.
- nombre, descripción y precio: por definir en la especificación funcional.
Relaciones: pertenece al catálogo de un vendedor; puede tener inventario distribuido si es físico.

### Inventory
Existencias de un producto en una bodega específica.
- `id`
- `productId`
- `warehouseId`
- `quantity`: existencias disponibles, sin permitir valores negativos.
- movimientos: ingreso, reserva, salida por venta, ajuste y devolución.
Relaciones: vinculado obligatoriamente a un producto y una bodega.

### Cart y CartItem
Carrito provisional de selección de productos.
- Cart: `id`, `buyerId`, estado (por definir).
- CartItem: `productId`, cantidad (por definir).
La especificación confirma el carrito como etapa provisional, pero no define sus campos completos.

### Order
Compromiso comercial formal iniciado por el comprador.
- `id`, `buyerId`, líneas de pedido, estado y fechas (campos detallados por definir).
Estados descritos: Carrito, Pendiente de Pago, Pagado, Despachado, Entregado/Finalizado.
Un pedido finalizado es inmutable.

### Invoice
Información comercial asociada a una compra. Atributos, numeración, impuestos y reglas de emisión: por definir.

### Shipment
Proceso logístico para productos físicos: preparación, empaque, despacho y transporte. Atributos y estados específicos: por definir.

### ReturnRequest y Refund
Representan devoluciones y reembolsos incluidos en el alcance. Causales, políticas, estados, plazos y reglas de aprobación: por definir.

### AdministrativeReport
Proyección de información consolidada para consulta administrativa. Indicadores y filtros: por definir.

## Relaciones principales
- User 1 — 0..1 Buyer / Seller / LogisticOperator / Administrator / Supervisor (un rol por usuario).
- Seller 1 — 0..* Product.
- Warehouse 1 — 0..* Inventory.
- Product 1 — 0..* Inventory.
- Buyer 1 — 0..* Cart y Order.
- Cart 1 — 1..* CartItem (cuando contiene artículos).
- Order 1 — 0..1 Invoice (relación propuesta; cardinalidad exacta pendiente).
- Order 1 — 0..* Shipment (si se permiten envíos parciales, por definir).
- Order 1 — 0..* ReturnRequest / Refund (política pendiente).

## Reglas de integridad
El inventario no puede ser negativo; no se reserva stock inexistente ni dañado; el pedido finalizado no se modifica; identidad y correo de usuario son únicos; cada usuario posee un solo rol.

# Input Ports — Interfaces de entrada por rol

## Principios
Los puertos representan las acciones disponibles para cada rol. La matriz funcional de la especificación es la referencia de autorización. Las operaciones exactas que no estén detalladas se mantienen pendientes de refinamiento.

## Puertos
### PublicAccessPort
- consultar catálogo público.
- iniciar sesión (la autenticación técnica queda fuera de la especificación funcional).

### BuyerPort
- administrar perfil y direcciones propias.
- consultar productos publicados.
- gestionar carrito.
- confirmar pedido.
- consultar pedidos propios.
- consultar información de facturación, envío, devolución y reembolso propia, según permisos definidos.

### SellerPort
- registrar y administrar productos propios.
- consultar productos y pedidos relacionados con su operación.
- participar en la administración de inventario según permisos del negocio.

### LogisticOperatorPort
- consultar tareas logísticas asignadas.
- gestionar preparación, empaque y despacho.
- registrar movimientos logísticos permitidos.

### AdministratorPort
- registrar vendedores.
- registrar y administrar bodegas.
- administrar usuarios y procesos administrativos incluidos en el alcance.

### SupervisorPort
- consultar y realizar seguimiento operativo.
- consultar reportes administrativos según autorización.

## Seguridad
Cada petición protegida debe asociarse a un usuario y a un único rol. La autorización debe comprobar rol y alcance del recurso. La especificación no define tecnología de autenticación ni tokens.

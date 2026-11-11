# Actividad: Sistema de Gestión Comercial NexusMarket

## Introducción y objetivo
NexusMarket es una plataforma digital centralizada que actúa como intermediario comercial entre compradores y vendedores. Administra la operación desde el registro de usuarios y publicación de productos hasta logística, facturación y posventa, con trazabilidad y coordinación entre participantes.

El sistema debe administrar usuarios, vendedores, compradores, bodegas, catálogo, inventario distribuido, carrito, pedidos, facturación, envíos, devoluciones, reembolsos y consultas administrativas.

## Roles del negocio
- **Comprador:** adquiere productos publicados y administra su información comercial y direcciones de entrega.
- **Vendedor:** registra y administra sus productos. No puede auto-registrarse; debe ser incorporado por el Administrador.
- **Operador Logístico:** atiende la operación física de bodegas y despachos.
- **Administrador:** registra vendedores y bodegas y administra los elementos que la especificación asigna a su función.
- **Supervisor:** consulta y realiza seguimiento operativo.

Cada participante tiene un único rol y solo puede interactuar con información correspondiente a sus funciones.

## Flujo general
1. El Administrador registra al vendedor y su primera bodega.
2. El vendedor registra productos y define sus características.
3. Se registran existencias iniciales en las bodegas asociadas.
4. Los productos se publican en el catálogo.
5. El comprador selecciona productos en el carrito y confirma el pedido.
6. Se valida el pago y comienza la preparación.
7. Se empaca, despacha y transporta el pedido.
8. El pedido se finaliza tras confirmar la entrega.

## Dominios funcionales
1. Administración de usuarios.
2. Gestión de compradores.
3. Gestión de vendedores.
4. Gestión de bodegas.
5. Gestión del catálogo.
6. Gestión del inventario.
7. Gestión de pedidos y carrito.
8. Facturación.
9. Envíos y logística.
10. Devoluciones y reembolsos.
11. Reportes administrativos.

Los dominios 8 a 11 se incluyen porque aparecen en el alcance funcional; la especificación no detalla todos sus atributos ni estados.

## Reglas y restricciones
- Toda operación debe ejecutarse por un usuario autenticado.
- Cada usuario tendrá un único rol.
- Ningún participante administrará información fuera de su rol.
- El identificador de usuario y el correo electrónico serán únicos.
- Los vendedores son incorporados por el Administrador.
- El inventario debe vincularse a un producto y una bodega.
- No se permiten existencias negativas.
- No se reserva inventario inexistente o marcado como dañado.
- Un pedido finalizado no puede modificarse.
- Los productos pueden ser físicos o digitales; los físicos requieren inventario y despacho y los digitales se entregan tras el pago.

## Alcance y exclusiones
Se incluyen registro y administración de usuarios, vendedores, compradores, bodegas, catálogo, inventario, carrito, pedidos, facturación, envíos, devoluciones, reembolsos y reportes administrativos. La especificación excluye interfaces gráficas, aplicaciones móviles, portales web, mecanismos técnicos de autenticación, tecnologías de implementación, arquitectura y almacenamiento. Estos últimos aspectos se proponen en el SDD como diseño técnico, no como requisitos originales del negocio.

# Order Services

## Responsabilidad
Gestionar el compromiso comercial desde la selección provisional hasta la entrega confirmada.

## Ciclo de estados
`CART` → `PENDING_PAYMENT` → `PAID` → `SHIPPED` → `DELIVERED/FINALIZED`.

## Reglas
- El pedido pendiente de pago espera confirmación financiera.
- El estado pagado inicia los procesos de alistamiento.
- El despacho representa la salida física de la bodega.
- La entrega confirmada permite finalizar el pedido.
- Un pedido finalizado no puede modificarse bajo ninguna circunstancia.

## Consideraciones
La especificación no describe cancelaciones, pagos fallidos, pedidos con productos de varios vendedores ni envíos parciales. No se deben implementar transiciones adicionales sin decisión funcional.

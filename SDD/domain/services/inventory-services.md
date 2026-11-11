# Inventory Services

## Responsabilidad
Administrar existencias distribuidas y vinculadas obligatoriamente a un producto y una bodega.

## Movimientos
`INFLOW`, `RESERVATION`, `SALE`, `ADJUSTMENT`, `RETURN`.

## Validaciones obligatorias
1. No permitir cantidades negativas.
2. No reservar inventario inexistente.
3. No reservar inventario en condición `DAMAGED`.
4. Cada registro de inventario debe referenciar un producto y una bodega.

## Flujo propuesto
Consultar existencia disponible → validar condición y cantidad → registrar movimiento → actualizar existencia → conservar trazabilidad del movimiento.

La fuente no define reglas de concurrencia, lotes, umbrales de reserva ni tratamiento de cancelaciones; quedan por definir.

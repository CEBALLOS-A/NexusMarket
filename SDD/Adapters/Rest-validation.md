# REST Validation Specification

## Validaciones funcionales
- Identificador de usuario obligatorio y único.
- Nombre completo obligatorio y no vacío.
- Correo obligatorio y único.
- Un solo rol por usuario.
- Estado de usuario dentro del catálogo definido.
- Tipo de producto limitado a físico o digital.
- Estado de producto limitado a publicado, suspendido o descontinuado.
- Inventario vinculado a producto y bodega.
- Cantidad de inventario nunca negativa.
- No reservar existencias inexistentes o dañadas.
- No modificar pedidos finalizados.

## Validación de transporte
Los DTOs deben validar campos obligatorios, formatos y tipos. La unicidad debe comprobarse en el dominio/servicio y garantizarse en almacenamiento para evitar duplicados concurrentes.

La fuente no define longitudes, formatos de dirección, moneda, impuestos ni formatos completos de productos; no inventarlos como reglas de negocio.

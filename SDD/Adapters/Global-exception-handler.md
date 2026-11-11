# Global Exception Handler Specification

## Propósito
Traducir errores de aplicación a respuestas HTTP consistentes.

## Categorías sugeridas
- `ValidationException`: datos inválidos.
- `DuplicateUserDataException`: identificador o correo duplicado.
- `UnauthorizedRoleException`: rol no autorizado.
- `ResourceNotFoundException`: usuario, producto, bodega, pedido o recurso inexistente.
- `InsufficientInventoryException`: stock insuficiente o dañado.
- `FinalizedOrderModificationException`: intento de modificar pedido finalizado.
- `BusinessRuleException`: regla de negocio incumplida.

## Respuesta estándar propuesta
`timestamp`, `status`, `code`, `message`, `path`, `details`.

Los códigos HTTP concretos son decisión de diseño y deben estandarizarse durante la implementación.

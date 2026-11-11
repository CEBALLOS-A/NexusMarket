# REST Adapters Specification

## Propósito
Exponer las funcionalidades del Marketplace mediante una API HTTP. La API es una propuesta técnica; la especificación funcional no define endpoints ni protocolo.

## Controladores propuestos
- `PublicCatalogController`
- `BuyerController`
- `SellerController`
- `LogisticOperatorController`
- `AdministratorController`
- `SupervisorController`
- `OrderController`
- `AfterSalesController`

## Convenciones
- Prefijo sugerido: `/api/v1`.
- DTOs separados para solicitudes y respuestas.
- Validación de entrada en la capa REST.
- Los controladores delegan en puertos de entrada.
- No exponer entidades de persistencia.

## Rutas ilustrativas
- `GET /api/v1/catalog/products`
- `POST /api/v1/buyer/cart/items`
- `POST /api/v1/buyer/orders`
- `GET /api/v1/buyer/orders/{orderId}`
- `POST /api/v1/admin/sellers`
- `POST /api/v1/admin/warehouses`
- `POST /api/v1/seller/products`
- `POST /api/v1/logistics/shipments/{shipmentId}/dispatch`
- `GET /api/v1/supervisor/reports`

Las rutas son ejemplos de diseño y deben validarse con el contrato funcional definitivo.

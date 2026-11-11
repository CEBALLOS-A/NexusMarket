# API REST Endpoints Specification & Contracts

## 1. Overview
Esta API es una propuesta para organizar las funciones de NexusMarket. No constituye un contrato ya aprobado por el negocio.

## 2. Convenciones
- Base path: `/api/v1`.
- JSON para solicitudes y respuestas.
- Identificadores en rutas.
- Validar rol y alcance del recurso.
- Respuestas de error uniformes.

## 3. Catálogo público
- `GET /catalog/products`: consultar productos publicados.

## 4. Comprador
- `GET /buyers/me`
- `PATCH /buyers/me`
- `GET /buyers/me/addresses`
- `POST /buyers/me/cart/items`
- `GET /buyers/me/cart`
- `POST /buyers/me/orders`
- `GET /buyers/me/orders`
- `GET /buyers/me/orders/{orderId}`

## 5. Vendedor
- `GET /sellers/me/products`
- `POST /sellers/me/products`
- `PATCH /sellers/me/products/{productId}`
- `PATCH /sellers/me/products/{productId}/status`

## 6. Administrador
- `POST /admin/sellers`
- `GET /admin/sellers`
- `POST /admin/warehouses`
- `GET /admin/warehouses`
- `GET /admin/users`

## 7. Operador logístico
- `GET /logistics/shipments`
- `POST /logistics/shipments/{shipmentId}/pack`
- `POST /logistics/shipments/{shipmentId}/dispatch`
- `POST /logistics/shipments/{shipmentId}/confirm-delivery`

## 8. Supervisor
- `GET /supervisor/operations`
- `GET /supervisor/reports`

## 9. Facturación y posventa
- `GET /orders/{orderId}/invoice`
- `POST /orders/{orderId}/returns`
- `GET /returns/{returnId}`
- `GET /refunds/{refundId}`

## 10. Pendientes de contrato
La fuente no especifica campos completos de DTO, códigos HTTP, reglas de pago, impuestos, estados de devolución, proveedor logístico ni permisos detallados por endpoint. Estos elementos deben definirse antes de considerar la API definitiva.

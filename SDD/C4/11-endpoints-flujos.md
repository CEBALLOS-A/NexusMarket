# Flujos por endpoint — NexusMarket

## Catálogo
`GET /api/v1/catalog/products` consulta productos publicados.

## Compra
`POST /api/v1/buyer/cart/items` → `GET /api/v1/buyer/cart` → `POST /api/v1/buyer/orders` → validación de disponibilidad → pago → alistamiento.

## Incorporación de vendedor
`POST /api/v1/admin/sellers` → validación de datos únicos → registro de vendedor → registro de bodega inicial según flujo de negocio.

## Publicación de producto
`POST /api/v1/seller/products` → validar vendedor → guardar producto → registrar inventario asociado a bodega → publicar.

## Logística
Pedido pagado → preparación/empaque → despacho → transporte → confirmación de entrega → pedido finalizado.

## Posventa
Devolución y reembolso están dentro del alcance, pero el flujo detallado, causales y estados deben definirse.

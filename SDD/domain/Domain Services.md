# Domain Services — NexusMarket

## Propósito
Los servicios concentran reglas de negocio que coordinan más de una entidad y son invocados por los casos de uso.

## Servicios propuestos
- `UserManagementService`: registrar y administrar usuarios, validar identificador/correo únicos y rol único.
- `BuyerManagementService`: administrar datos del comprador y direcciones, respetando su ámbito de acceso.
- `SellerManagementService`: incorporación administrativa y mantenimiento de vendedores.
- `WarehouseManagementService`: registrar y administrar bodegas del Marketplace y de vendedores.
- `CatalogService`: registrar productos, variantes y estados; diferenciar físicos y digitales.
- `InventoryService`: registrar movimientos y validar disponibilidad, condición y no negatividad.
- `CartService`: agregar y retirar productos y preparar la confirmación del pedido.
- `OrderService`: controlar transiciones de estado y bloquear modificaciones al finalizar.
- `BillingService`: administrar la información de facturación asociada a compras.
- `LogisticsService`: coordinar preparación, empaque, despacho y transporte.
- `AfterSalesService`: gestionar devoluciones y reembolsos.
- `AdministrativeReportingService`: consolidar información para consulta.

## Regla de diseño
La lógica crítica no debe quedar en controladores REST. Los servicios deben recibir modelos de dominio y utilizar puertos de salida para consultar o persistir información. Los detalles no definidos por la fuente (cálculo de impuestos, proveedor de pagos, transportadoras y políticas de devolución) requieren decisión de negocio antes de implementarse.

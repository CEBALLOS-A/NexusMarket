# C4 Nivel 4 — Código

## Clases principales
`User`, `Buyer`, `Seller`, `Warehouse`, `Product`, `Inventory`, `Cart`, `CartItem`, `Order`, `Invoice`, `Shipment`, `ReturnRequest`, `Refund`.

## Flujo de ejemplo
```mermaid
sequenceDiagram
 actor B as Comprador
 participant C as BuyerController
 participant U as BuyerUseCase
 participant O as OrderService
 participant I as InventoryService
 participant R as Repositories
 B->>C: Confirmar pedido
 C->>U: createOrder()
 U->>O: validar y crear
 O->>I: reservar existencias
 I->>R: consultar/registrar movimiento
 O->>R: guardar pedido
 R-->>B: resultado
```
La confirmación de pago y sus integraciones requieren definición adicional.

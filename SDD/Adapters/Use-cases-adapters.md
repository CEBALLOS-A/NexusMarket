# Use Cases Adapters Specification

## Propósito
Implementar los puertos de entrada y coordinar servicios de dominio sin contener reglas comerciales duplicadas.

## Implementaciones sugeridas
- `BuyerUseCaseImpl`
- `SellerUseCaseImpl`
- `LogisticOperatorUseCaseImpl`
- `AdministratorUseCaseImpl`
- `SupervisorUseCaseImpl`
- `PublicAccessUseCaseImpl`

## Dependencias
Cada caso de uso recibe servicios de dominio y devuelve modelos de dominio o resultados de aplicación. No debe depender directamente de controladores ni de entidades de persistencia.

## Ejemplo de flujo
`BuyerController` → `BuyerPort` → `BuyerUseCaseImpl` → `CartService` / `OrderService` → `InventoryRepositoryPort` y `OrderRepositoryPort`.

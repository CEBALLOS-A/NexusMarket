# Capa — Adapters de casos de uso

| Puerto | Implementación propuesta |
|---|---|
| PublicAccessPort | PublicAccessUseCaseImpl |
| BuyerPort | BuyerUseCaseImpl |
| SellerPort | SellerUseCaseImpl |
| LogisticOperatorPort | LogisticOperatorUseCaseImpl |
| AdministratorPort | AdministratorUseCaseImpl |
| SupervisorPort | SupervisorUseCaseImpl |

Los casos de uso delegan reglas a servicios de dominio y usan Output Ports para persistencia.

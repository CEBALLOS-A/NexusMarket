# Software Architecture — NexusMarket

## Overview
Se propone una arquitectura hexagonal (Ports and Adapters) combinada con principios DDD. Esta decisión técnica organiza la solución y mantiene las reglas comerciales separadas de HTTP, persistencia y frameworks. La especificación funcional no impone una arquitectura; por tanto, esta sección constituye una propuesta del SDD.

## Principios
- Dominio independiente de tecnologías externas.
- Separación de responsabilidades.
- Inversión de dependencias.
- Alta cohesión y bajo acoplamiento.
- Control de acceso según rol.
- Trazabilidad de operaciones relevantes.

## Capas
1. **Domain:** entidades, objetos de valor, enumeraciones, servicios y puertos.
2. **Adapters:** REST, casos de uso y persistencia.
3. **Infrastructure:** configuración, conexiones y seguridad técnica.
4. **Application:** punto de entrada y ensamblado.

## Estructura propuesta
```text
src/main/java/com/nexusmarket/
├── App.java
├── domain/
│   ├── models/
│   ├── valueobjects/
│   ├── enums/
│   ├── services/
│   ├── exceptions/
│   └── ports/
│       ├── in/
│       └── out/
├── adapters/
│   ├── rest/
│   │   ├── controllers/
│   │   ├── dtos/requests/
│   │   ├── dtos/responses/
│   │   └── mappers/
│   ├── useCases/
│   └── persistence/
│       ├── jpa/entities/
│       ├── jpa/mappers/
│       ├── jpa/repositories/
│       ├── jpa/adapters/
│       └── mongodb/
└── infrastructure/
    ├── config/
    ├── security/
    └── database/
```

## Responsabilidades
### Domain
Contiene `User`, `Buyer`, `Seller`, `Warehouse`, `Product`, `Inventory`, `Cart`, `Order`, `Invoice`, `Shipment`, `ReturnRequest` y `Refund`. Las reglas de inventario, roles y ciclo de pedido deben residir aquí.

### REST adapters
Exponen los contratos HTTP y convierten DTOs a modelos de dominio. Los controladores no contienen reglas comerciales.

### Use case adapters
Implementan los puertos de entrada, coordinan servicios de dominio y utilizan los puertos de salida.

### Persistence adapters
La implementación actual usa `DatabaseService` como adaptador JDBC + MongoDB. SQL Server almacena usuarios y productos en `NexusMarket.dbo.NM_Users` y `NexusMarket.dbo.NM_Products`; MongoDB usa las colecciones `users` y `products` de la base `NexusMarket`. Las operaciones de sincronización usan upsert. La cobertura de pedidos, inventario y demás agregados queda pendiente hasta conectar sus flujos de aplicación. Ver `Adapters/Persistence-adapters.md` y `DATABASE_SETUP.md`.

### Infrastructure
Configura el framework, seguridad técnica y conexiones externas. La autenticación técnica no está especificada funcionalmente; se debe definir durante el diseño detallado.

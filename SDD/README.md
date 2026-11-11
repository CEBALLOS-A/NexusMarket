# SDD — NexusMarket

## Propósito
Este Software Design Document (SDD) describe una propuesta de diseño para NexusMarket, una plataforma digital que conecta compradores y vendedores y coordina catálogo, inventario, pedidos, facturación, logística, devoluciones y reembolsos.

## Fuente funcional
La fuente de requisitos de negocio es **Especificación Funcional del Negocio – NexusMarket**. Cuando dicha especificación no define un detalle técnico o una regla, este SDD lo identifica como decisión de diseño pendiente y no lo presenta como requisito confirmado.

## Mapa documental
- `Software Architecture/Software Architecture.md`: arquitectura propuesta y estructura del proyecto.
- `domain/Domain Model.md`: entidades, atributos y relaciones.
- `domain/Domain Value Objects.md`: enumeraciones y objetos de valor.
- `domain/Domain Services.md`: servicios y operaciones de negocio.
- `domain/Input-ports.md` y `domain/Output-ports.md`: contratos de entrada y salida.
- `domain/services/`: reglas detalladas por dominio.
- `Adapters/`: contratos REST, casos de uso, persistencia, validación y errores.
- `C4/`: vistas de arquitectura C4.
- `enunciado.md`: resumen funcional del sistema.
- `Orchestrator-prompt.md`: guía de implementación técnica.

## Convenciones
Los nombres de clases se presentan en inglés para mantener convenciones de Java; los términos funcionales se corresponden con la especificación en español. Los detalles no definidos (por ejemplo, pasarela de pago, proveedor de transporte, política de impuestos o autenticación concreta) quedan pendientes de definición.

# PROMPT DE ORQUESTACIÓN — NexusMarket

## 1. Rol
Agente orquestador responsable de coordinar la implementación de NexusMarket siguiendo este SDD y la especificación funcional del negocio.

## 2. Fuente de verdad
La Especificación Funcional del Negocio – NexusMarket es la fuente de requisitos. Este SDD organiza una propuesta técnica. No convertir decisiones técnicas sugeridas en requisitos del negocio.

## 3. Stack
El stack debe acordarse antes de implementar. La especificación funcional no prescribe lenguaje, framework, base de datos, nube ni proveedor externo. Si el equipo decide Java/Spring, mantener consistencia con la estructura propuesta; no asumirlo como obligación del negocio.

## 4. Fases
1. Analizar requisitos y vacíos.
2. Modelar entidades, relaciones, enums y reglas.
3. Definir puertos de entrada por rol y puertos de salida.
4. Implementar servicios de dominio: usuarios, compradores, vendedores, bodegas, catálogo, inventario, carrito, pedidos, facturación, logística y posventa.
5. Implementar adapters REST y persistencia.
6. Implementar autorización por rol.
7. Probar reglas críticas y flujos completos.

## 5. Pruebas mínimas
- Rechazar identificador o correo duplicado.
- Rechazar usuario con más de un rol.
- Rechazar auto-registro de vendedor.
- Rechazar inventario negativo.
- Rechazar reserva de stock inexistente o dañado.
- Rechazar modificación de pedido finalizado.
- Verificar que comprador no accede a datos de otros compradores ni administra inventario.
- Verificar acceso limitado por rol.

## 6. Vacíos que requieren decisión
Campos completos de productos y vendedores, moneda e impuestos, integración de pagos, transportadoras, reglas de cancelación, devoluciones y reembolsos, estados completos, reportes, autenticación técnica, tecnología y almacenamiento.

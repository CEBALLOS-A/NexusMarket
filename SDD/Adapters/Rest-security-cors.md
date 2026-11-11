# REST Security and CORS Specification

## Objetivo
Proteger las operaciones y limitar el acceso según el rol del participante.

## Reglas funcionales de autorización
- Cada operación protegida corresponde a un usuario autenticado.
- Cada usuario tiene un único rol.
- El acceso se limita a las responsabilidades de ese rol.
- Compradores acceden a su propia información; no administran otros compradores ni inventario.
- El Administrador incorpora vendedores.
- El Supervisor dispone de consulta y seguimiento operativo.

## Decisiones técnicas pendientes
La especificación excluye los mecanismos técnicos de autenticación. Por ello, JWT, OAuth2, sesiones, CORS y políticas de origen no son requisitos confirmados; deberán definirse en el diseño técnico y documentarse por separado.

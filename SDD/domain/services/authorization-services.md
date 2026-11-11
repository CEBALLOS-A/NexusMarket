# Authorization Services

## Objetivo
Garantizar que cada participante opere únicamente dentro de las funciones asignadas a su rol.

## Reglas confirmadas
- Un usuario tiene un único rol.
- Ningún participante administra información fuera de su rol.
- El comprador no administra información de otros compradores ni inventarios.
- El vendedor no se auto-registra; lo incorpora el Administrador.
- El Supervisor tiene perfil de consulta y seguimiento operativo.

## Diseño
`AuthorizationService` valida identidad, rol y pertenencia del recurso antes de ejecutar una operación. La autorización por recurso (por ejemplo, comprador dueño de un pedido o vendedor dueño de un producto) debe aplicarse además del control general por rol.

La especificación no detalla permisos individuales de cada endpoint; deben precisarse en una matriz de autorización antes de implementación.

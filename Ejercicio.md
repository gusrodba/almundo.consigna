# almundo.consigca - CallCenter
Existe un call center donde hay 3 tipos de empleados: operador, supervisor y director. El proceso de la atención de una llamada telefónica en primera instancia debe ser atendida por un operador, si no hay ninguno libre debe ser atendida por un supervisor, y de no haber tampoco supervisores libres debe ser atendida por un director.

# Extras/Plus
* Dar alguna solución sobre que pasa con una llamada cuando no hay ningun empleado libre.
Rta: Para dar solución a la problematica de llamadas donde no se encuentran empleados libres, pienso que la mejor manera seria programar la llamada para que el call la devuelva cuando se encuentren empleados libres.
Me tome el trabajo de hacer la implementación de esta solución, marcando las llamadas q ingresan y a las cuales no se les encuentra quein las atienda para que al momento de haber empleados libres esta sea realizada.

* Dar alguna solución sobre	qué	pasa con una llamada cuando	entran más de 10 llamadas concurrentes.
Rta: AL igual que para el punto anterior, la programación de llamadas soluciona este probllema.

* Agregar los tests unitarios que sean convenientes.
Rta: Durante la ejecucion del test se ejecutan 2 escenarios.
1. Donde hay suficientes empleados que respondan el ingreso de las 10 llamadas.
2. Donde hay menos empleados que llamadas entrantes.

* Agregar documentacion de código.
Rta: EL codigo se encuentra documentado.

# Nota:
Se imprime un log en la consola con el movimiento de las llamadas.

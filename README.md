# OMAR MANCILLAS LUNA 16100226

# PUNTOS A CURBIR

## ·INTENCION EXPLICITA
  Para este punto se puede encontrar en muchas de las Activities, cuando se intenta llamara a otra Activity.

## · INTENCION IMPLICITA
  Esto se muestra en la clase de RecyclerViewActivity en la parte del onLongItemClickListener, se manda llamar una intencion implicita
  para abrir la aplicaicion de telefono.

## · SNACKBAR
  Cuando se selecciona un item del RecyclerView de los restaurantes, en la siguiente Vista, aparece un Snackbar
  
## · RECYCLERVIEW
  Se utilizaron dos RecyclerViews, uno para la lista de restaurantes y otro para la lista de Comidad por cada restaurante.

## · FRAGMENT***

## · CUADRO DE DIALOGO
  Se muestran dos cuadros de dialogo, uno al momento de querer registrar sin haber llenado los campos, y otroo cuando pide confirmacion 
  de pedido

## · PREFERENCIAS CON PREFERENCESCREEN/SHAREDPREFERENCES***

## · ALMACENAMIENTO EN MEMORIA INTERNA
  Cuando alguien se registra se guardan sus datos en un archivos, en memoria interna.
  
## · ALMACENAMIENTO EN MEMORIA EXTERNA
  En la opcion Cuenta dle Menu Principal, viene una opcion para escoger foto, cuando esocgemos una foto, esta se guarda en memoria externa.
  El plan era hacerlo al momento de tomar una foto, pero tuve varias complicaciones.

## · ROOM
  El Room se utilizo para el ingreso y registro de usuarios.
  Adicional a esto, tambien se hizo uso de SQLite, par manerja una base de datos ya precargada con la aplicacion.

## · SERVICIO***

## · TAREA EN SEGUNDO PLANO (WORKMANAGER, JOBSCHEDULER, ALARM)
  Se menajo una alarma que se enciende en el momento en que el usuario llega a la vista de la comida por restaurantes

## · CONSUMIR ALGUN SERVICIO WEB REST (POST, GET, PUT, DELETE)
  Para el servicio web fue algo muy simple, solamente se establecio el tipo de cambio antes de confirmar la compra, arriba del boton
  Ordenar.

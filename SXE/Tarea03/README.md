1. Para descargar alpine sin arrancarlo utilizamos docker pull alpine

comprobamos la descarga con docker images
![img.png](img.png)


2. Para crear un contenedor sin nombre utilizamos docker create alpine(con una sola vez sirve, yo lo hice 2 sin querer), con docker ps mostramos los contenedores en ejecucion, con ps -a se ven todos los creados.

![img_2.png](img_2.png)

3. Para crear un contenedor con el nombre dam_alp1 y acceder a el, tienes q escribir docker run -it --name (aqui el nombre sin parentesis) alpine. Cuando lo crees entrarás automaticamente a él, para salir puedes escribir exit.

![img_3.png](img_3.png)

4.
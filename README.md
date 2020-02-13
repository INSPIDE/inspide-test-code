# Inspide Test Code

## Objetivos

Los objetivos generales de este _Test Code_ son los siguientes:

* En la carpeta **data** hay una serie de ficheros en formato GPX que contienen tracks de rutas en las que se han
  grabando una serie de posiciones (latitudes y longitudes) con un dispositivo GPS.
  
* Para cada fichero se pide calcular:
  
  1. El número total de puntos.
  2. La distancia total de todos los puntos. (la suma de distancias entre punto y punto).
  3. La distancia media entre punto y punto. (la suma de distancias entre punto y punto / Número total de segmentos).
  4. La distancia mínima entre un punto y otro, teniendo en cuenta todos los segmentos.
  5. La distancia máxima entre un punto y otro, teniendo en cuenta todos los segmentos.
  
  Nota: un segmento corresponde a la distancia entre un punto y el siguiente punto.
  
* Una vez obtenidos los resultados para cada fichero debemos realizar el calculo y mostrarlos en formato CSV separados
  por tabuladores y ordenados de mayor a menor distancia.
  
  Ejemplo:
  
  Dataset después de parsear latitudes y longitudes y calcular la distancia entre puntos:
  
  DistanciasEntrePuntoyPunto(1.gpx) = 2,6,3,4,5,1,7 (8 puntos, 7 segmentos)
  
  DistanciasEntrePuntoyPunto(2.gpx) = 1,2,1,2       (5 puntos, 4 segmentos)
  
  |    id | totalNumberOfPoints | totalDistance | avgDistance | minDistance | maxDistance |
  | ----: | ------------------: | ------------: | ----------: | ----------: | ----------: |
  | 1.gpx |           8         |      28       |      4      |      1      |      7      |  
  | 2.gpx |           5         |       6       |      3.5    |      1      |      2      |
  |   ... |         ...         |     ...       |      ...    |    ...      |    ...      |
  
## Consideraciones

Para realizar este _Test Code_ hay que tener en cuenta las siguientes consideraciones:

1. Es obligatorio crear un proyecto utilizando sbt que genere un fat jar que pueda ser ejecutado desde la línea de
   comandos.
   
   Tambien es obligatorio generar un docker donde se levante el jar.
   
   Sólo se utilizarán los siguientes comandos para comprobar la solución aportada:
   
   ```shell script
   $ sbt clean assembly                            # Comando que ejecutaremos para generar el fat jar
   $ java -jar target/scala-2.12/solution.jar data # Comando para parsear todos los archivos GPX en el directorio data y mostrar los resultados pedidos
   ```
   
2. Es obligatorio utilizar Scala 2.12 para la solución.

3. Sólo esta permitido utilizar librerias estandar de Scala y librerias de libre elección para parsear el GPX y para
   calcular la distancia entre dos puntos dada su latitud y longitud.
   
4. No es necesario mostrar los resultados en formato tabla, con que este separado con un tabulador es suficiente.

5. Forkear este repositorio como un repositorio privado en Github y crear una pull request sobre el fork (no sobre este
   repositorio) con la solución aportada.



## Recomendaciones

Tener en cuenta estas recomendaciones generales que tendremos en cuenta a la hora de evaluar la solución:

* Nos gusta el codigo sencillo, funcional y fácil de leer.
* Nos gusta el codigo testado y documentado.
* Si quieres dar alguna explicación detallada puedes crear un fichero README_DEV.md en la raíz del proyecto.


**Luck and may the force be with you!**
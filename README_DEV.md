#Documentación
No he comentado el código porque creo que es bastante sencillo. No obstante los tests creo que explican bastante sobre 
lo que hace cada función.

#Errores en stderr 
Aunque no he entrado en profundidad, diría que la librería que parsea GPX tiene un error de programación. En 
https://github.com/himanshu-soni/gpx-parser/blob/master/src/main/java/me/himanshusoni/gpxparser/BaseGPX.java#L15 crea un
SimpledateFormat estático y SimpleDateFormat no es thread-safe. Normalmente yo no usaría Future, usaría Task de Monix o
IO de Cats para ejecutar los side-effects. He usado Future para ceñirme estríctamente al enunciado sobre el uso de
librerías.

Para ver mejor los resultados sugiero usar
```scala
java -jar target/scala-2.12/solution.jar data 2> /dev/null 
```

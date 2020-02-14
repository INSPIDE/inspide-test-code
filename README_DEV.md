# Compilación
La primera vez que se ejecute sbt clean assembly puede ocurrir que falle la descarga de una librería   
(javax.media#jai_core;1.1.3!jai_core.jar) que esta en las dependencias transitivas. ***Ejecutar de nuevo*** y no debería  
volver a fallar.  


# Documentación
No he comentado el código porque creo que es bastante sencillo. No obstante los tests creo que explican bastante sobre   
lo que hace cada función.  


# Errores en stderr
Aunque no he entrado en profundidad, diría que la librería que parsea GPX tiene un error de programación. En   
https://github.com/himanshu-soni/gpx-parser/blob/master/src/main/java/me/himanshusoni/gpxparser/BaseGPX.java#L15 crea un  
SimpledateFormat estático y SimpleDateFormat no es thread-safe. Normalmente yo no usaría Future, usaría Task de Monix o  
IO de Cats para ejecutar los side-effects. He usado Future para ceñirme estríctamente al enunciado sobre el uso de  
librerías.  
  
Para ver mejor los resultados sugiero usar 
 
```scala  
java -jar target/scala-2.12/solution.jar data 2> /dev/null
```  
  
Si es necesario podría buscar otra librería para parsear los GPX  
  
# Docker
No tengo muy claro como se esperaba la creación del contenedor de docker. Así que lo he hecho muy sencillo.  
Para crear la imagen (tras crear el fatjar)  

```bash  
docker build -t solution:1.0.0 -f src/main/docker/Dockerfile .  
```  
Para ejecutar la aplicación   
```bash  
docker run --rm -it -v ${PWD}/data:/opt/data solution:1.0.0  
```

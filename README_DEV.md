# Compilación
La primera vez que se ejecute sbt clean assembly puede ocurrir que falle la descarga de una librería   
(javax.media#jai_core;1.1.3!jai_core.jar) que esta en las dependencias transitivas. ***Ejecutar de nuevo*** y no debería  
volver a fallar.  


# Documentación
No he comentado el código porque creo que es bastante sencillo. No obstante los tests creo que explican bastante sobre   
lo que hace cada función.  
  
  
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

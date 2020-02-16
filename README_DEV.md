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

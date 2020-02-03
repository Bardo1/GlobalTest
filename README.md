# GlobalTest

Hola

Algunos puntos informativos de lo realizado,

1. Envío el archivo postman "GlobalTest.postman_collection" donde están guardadas las 2 peticiones y donde es 
 mas fácil utilizar, para realizar los test de llamadas. Solo se debe importar.

 Realicé dos enpoints de entrada:

 -. http://localhost:8080/persons (Post)
   Donde se envía el objeto de entrada especifico (Persona), para su ingreso en la base de datos de memoria H2

 -. http://localhost:8080/persons (Get)
   Para traer todas las personas ingresadas, con sus correspondientes números de teléfono

2. La base de datos es H2 por lo que la carga se realiza en memoria de forma automatica. 
   Además cargo dos entradas en el runtime de ejecución de inicio en la clase de arranque de spring boot

3. Envío la foto de un modelo de clases que muestra las dos entidades que utilizo y el diagrama de secuencia del ingreso de una persona y sus telefonos 

4. En ámbitos de programación hice las tres validaciones correspondientes y agregué un manejador de error
   que se encarga de capturar y enviar el correspondiente error.

5. Agregué Swagger para ser testeado de forma alternativa
   Se puede acceder por la siguiente URL del componente: http://localhost:8080/swagger-ui.html

6. Agregué los logs, las dos caracteristicas de java 8 y las correspondientes validaciónes para hacer el ingreso de una persona

7. Agrego dos diagramas realizados
   1. Diagrama de objetos, con sus atributos
   2. Diagrama de secuencias, donde sale detallado el proceso de la inserción de una persona.

8. con el comando "gradle bootRun" corre el servicio. Y con gradle clean test, los test correspondientes (Por otro lado, también puede ser ejecutada por por maven)



Eso como los puntos principales.

Atento a cualquier cosa

Gracias.
  

# README #

Este README contiene la información para ejeuctar el proyecto.

### Ejeuctar el proyecto ###

* Verificar la conexion a la base de datos
* Ejecutar el proyecto (./mvnw spring-boot:run) o desde el IDE
* Ejecutar el scrip data-insert.sql, de resources

### Obtener token ###

* Ejecutar con POSTMAN o similar
* Metodo POST a http://localhost:8080/login
* Lanzar el json con datos de usuario

```
{
"username": "brizuela@gmail.com"
"password": "12345"
}
```

### Librerias requeridas ###

* Spring web 2.7.12
* Spring security
* Postgres
* Jwt
* Lombock

### Quien puede ayudar? ###

* Julio Cesar Brizuela
* brizuelaalvarado@gmail.com
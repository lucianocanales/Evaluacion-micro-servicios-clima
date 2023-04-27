# Micro Servicio del Clima #

## Puesta en marcha

Para hacer la puesta en marcha debe:

1. Clonar el repositorio.
2. Tener instalado java en su vercion 8 si no lo puede obtener del siguiente [Link](https://www.oracle.com/ar/java/technologies/javase/javase8-archive-downloads.html).
3. Tener instalado maven que puede obtenerlo del siguiente [Link](https://maven.apache.org/download.cgi).
4. Además deberá tener instalada la base de datos H2 la puede obtener de [H2](https://www.h2database.com/html/main.html).
5. Debera tener una cuenta en https://developer.accuweather.com/.
6. Para poder obtener una API KEY, con la cual podrá hacer las Peticiónes a la API de accuweather. Deberá agregar una nueva App en la página con un nombre que haga referencia al microservicio, esto le dará una clave que deberá ingresar en .properties de ambos servicios.
7. Ahora en src\main\resources\application.properties edite las siguientes lineas:
    - spring.datasource.username= (**aquí va el usuario que tiene la base de datos, por defecto es sa**).
    - spring.datasource.password= (**aquí va la contraseña que tiene en la base de datos por defecto es vacío**).
    - spring.datasource.url=jdbc:h2:~/Weather (**aquí va la dirección de la base de datos, recomiendo no cambiar a menos que le genere problemas**).
    - spring.external.service.api-key= (**aquí va la API KEY de accuweather**).
8. Para que todo funcione correctamente deberemos configurar los dos archivos application.properties de cada micro servicio.
9. Una vez configurado ingresa en la carpeta locations abre una terminal/consola/powershell/ y ejecuta el siguiente comando:

        mvn spring-boot:run
    esto levantará el servidor de locations en el puerto 8081 si lo tiene ocupado puede que necesite cambiar el parámetro server.port del archivo application.properties del servido de locations si es ha si tambien debera corregir el archivo application.properties del servidor weather colocando el puerto correctamente en spring.internal.service.location-url=http://localhost:8081/api/v1/location/ e intente levantarlo de nuevo, una vez iniciado no cierre la consola.
10. Una vez funcionando correctamente el servicio de locations vamos a levantar el siguiente servicio nos dirigimos a la carpeta de weather y abrimos una nueva terminal y al igual que el anterior ejecuta el siguiente comando:
        
        mvn spring-boot:run
11. Con eso quedará andando el sistema ahora deberá usar usar postman o un navegador para hacer las consultas ya que todas las consultas son por método Get.


## Parte de la evaluación

- Hacer el desarrollo de un microservicio el cual contenga una api rest que nos brinde la información del clima. 

    - Realizar el modelo de clases y agregar las validaciones que crea necesarias.
    - Incluya un archivo con las indicaciones para configurar y ejecutar el proyecto. (README)
    - Agregar documentación de código.
    - Agregar los tests unitarios que se crean convenientes.


- El desarrollo debe estar en Java 1.8 , Spring Boot , Maven y base de datos relacional.

# API

## Lista de Locations

-   Modelo : Location
-   Path: domain:8081/api/v1/location
-   Acciones Permitidas: GET
-   Formato de respuest: JSON

Busca todos los registros de las ciudades en la base de datos local 

### Formato de Petición:

    http://localhost:8081/api/v1/location

### Formato de json devuelto:

    [
        {
            "id": 1,
            "key": "7493",
            "localizedName": "Pinamar"
        },
        {
            "id": 33,
            "key": "2927",
            "localizedName": "Pilar"
        },
        {
            "id": 65,
            "key": "7892",
            "localizedName": "La Plata"
        }
    ]

## Location por nombre

-   Modelo : Location
-   Path: domain:8081/api/v1/location/{name}
-   Acciones Permitidas: GET
-   Formato de respuest: JSON

Busca el registro de la ciudad en la base de datos local si no las encuentra las busca en la API de Accuweather.

### Formato de Petición:

    http://localhost:8081/api/v1/location/La Plata

### Formato de json devuelto:

    {
        "id": 98,
        "key": "7892",
        "localizedName": "La Plata"
    }

## Clima  de las próximas 12 hs

-   Modelo : Weather
-   Path: domain:8080/api:/v1/Weather/12hour/{name}
-   Acciones Permitidas: GET
-   Formato de respuest: JSON

Busca los registros del clima de las próximas 12 hs en la base de datos local si no los encuentra los busca en la API de Accuweather.

### Formato de Petición:

    http://localhost:8080/api/v1/Weather/12hour/pinamar


### Formato de json devuelto:

    [
        {
            "id": 38,
            "localizedName": "Pinamar",
            "weatherType": "H",
            "createDate": "2023-04-27T05:50:19.005+00:00",
            "weatherDate": "2023-04-27T06:00:00.000+00:00",
            "dayDescriptions": "",
            "nightDescriptions": "Clear",
            "dayHasPrecipitation": false,
            "nightHasPrecipitation": false,
            "maxTemperature": 57.0,
            "minTemperature": 57.0
        },
        {
            "id": 39,
            "localizedName": "Pinamar",
            "weatherType": "H",
            "createDate": "2023-04-27T05:50:19.043+00:00",
            "weatherDate": "2023-04-27T07:00:00.000+00:00",
            "dayDescriptions": "",
            "nightDescriptions": "Partly cloudy",
            "dayHasPrecipitation": false,
            "nightHasPrecipitation": false,
            "maxTemperature": 57.0,
            "minTemperature": 57.0
        },
        {
            "id": 40,
            "localizedName": "Pinamar",
            "weatherType": "H",
            "createDate": "2023-04-27T05:50:19.044+00:00",
            "weatherDate": "2023-04-27T08:00:00.000+00:00",
            "dayDescriptions": "",
            "nightDescriptions": "Intermittent clouds",
            "dayHasPrecipitation": false,
            "nightHasPrecipitation": false,
            "maxTemperature": 56.0,
            "minTemperature": 56.0
        },
        {
            "id": 41,
            "localizedName": "Pinamar",
            "weatherType": "H",
            "createDate": "2023-04-27T05:50:19.045+00:00",
            "weatherDate": "2023-04-27T09:00:00.000+00:00",
            "dayDescriptions": "",
            "nightDescriptions": "Mostly cloudy",
            "dayHasPrecipitation": false,
            "nightHasPrecipitation": false,
            "maxTemperature": 55.0,
            "minTemperature": 55.0
        },
        {
            "id": 42,
            "localizedName": "Pinamar",
            "weatherType": "H",
            "createDate": "2023-04-27T05:50:19.046+00:00",
            "weatherDate": "2023-04-27T10:00:00.000+00:00",
            "dayDescriptions": "",
            "nightDescriptions": "Cloudy",
            "dayHasPrecipitation": false,
            "nightHasPrecipitation": false,
            "maxTemperature": 54.0,
            "minTemperature": 54.0
        },
        {
            "id": 43,
            "localizedName": "Pinamar",
            "weatherType": "H",
            "createDate": "2023-04-27T05:50:19.047+00:00",
            "weatherDate": "2023-04-27T11:00:00.000+00:00",
            "dayDescriptions": "Cloudy",
            "nightDescriptions": "",
            "dayHasPrecipitation": false,
            "nightHasPrecipitation": false,
            "maxTemperature": 55.0,
            "minTemperature": 55.0
        },
        {
            "id": 44,
            "localizedName": "Pinamar",
            "weatherType": "H",
            "createDate": "2023-04-27T05:50:19.048+00:00",
            "weatherDate": "2023-04-27T12:00:00.000+00:00",
            "dayDescriptions": "Cloudy",
            "nightDescriptions": "",
            "dayHasPrecipitation": false,
            "nightHasPrecipitation": false,
            "maxTemperature": 57.0,
            "minTemperature": 57.0
        },
        {
            "id": 45,
            "localizedName": "Pinamar",
            "weatherType": "H",
            "createDate": "2023-04-27T05:50:19.049+00:00",
            "weatherDate": "2023-04-27T13:00:00.000+00:00",
            "dayDescriptions": "Mostly cloudy",
            "nightDescriptions": "",
            "dayHasPrecipitation": false,
            "nightHasPrecipitation": false,
            "maxTemperature": 60.0,
            "minTemperature": 60.0
        },
        {
            "id": 46,
            "localizedName": "Pinamar",
            "weatherType": "H",
            "createDate": "2023-04-27T05:50:19.050+00:00",
            "weatherDate": "2023-04-27T14:00:00.000+00:00",
            "dayDescriptions": "Intermittent clouds",
            "nightDescriptions": "",
            "dayHasPrecipitation": false,
            "nightHasPrecipitation": false,
            "maxTemperature": 63.0,
            "minTemperature": 63.0
        },
        {
            "id": 47,
            "localizedName": "Pinamar",
            "weatherType": "H",
            "createDate": "2023-04-27T05:50:19.051+00:00",
            "weatherDate": "2023-04-27T15:00:00.000+00:00",
            "dayDescriptions": "Mostly sunny",
            "nightDescriptions": "",
            "dayHasPrecipitation": false,
            "nightHasPrecipitation": false,
            "maxTemperature": 66.0,
            "minTemperature": 66.0
        },
        {
            "id": 48,
            "localizedName": "Pinamar",
            "weatherType": "H",
            "createDate": "2023-04-27T05:50:19.052+00:00",
            "weatherDate": "2023-04-27T16:00:00.000+00:00",
            "dayDescriptions": "Partly sunny",
            "nightDescriptions": "",
            "dayHasPrecipitation": false,
            "nightHasPrecipitation": false,
            "maxTemperature": 68.0,
            "minTemperature": 68.0
        },
        {
            "id": 49,
            "localizedName": "Pinamar",
            "weatherType": "H",
            "createDate": "2023-04-27T05:50:19.053+00:00",
            "weatherDate": "2023-04-27T17:00:00.000+00:00",
            "dayDescriptions": "Partly sunny",
            "nightDescriptions": "",
            "dayHasPrecipitation": false,
            "nightHasPrecipitation": false,
            "maxTemperature": 69.0,
            "minTemperature": 69.0
        }
    ]

## clima de los porximos 5 días

-   Modelo : Weather
-   Path: domain:8080/api/v1/Weather/5day/{name}
-   Acciones Permitidas: GET
-   Formato de respuest: JSON

Busca los registros del clima de los próximos 5 días en la base de datos local si no los encuentra los busca en la API de Accuweather.

### Formato de Petición

    http://localhost:8080/api/v1/Weather/5day/{name}

### Formato de json devuelto:

    [
        {
            "id": 2,
            "localizedName": "Pinamar",
            "weatherType": "D",
            "createDate": "2023-04-27T05:29:42.756+00:00",
            "weatherDate": "2023-04-27T10:00:00.000+00:00",
            "dayDescriptions": "Intermittent clouds",
            "nightDescriptions": "Mostly clear",
            "dayHasPrecipitation": false,
            "nightHasPrecipitation": false,
            "maxTemperature": 71.0,
            "minTemperature": 55.0
        },
        {
            "id": 3,
            "localizedName": "Pinamar",
            "weatherType": "D",
            "createDate": "2023-04-27T05:29:42.758+00:00",
            "weatherDate": "2023-04-28T10:00:00.000+00:00",
            "dayDescriptions": "Mostly sunny",
            "nightDescriptions": "Mostly clear",
            "dayHasPrecipitation": false,
            "nightHasPrecipitation": false,
            "maxTemperature": 81.0,
            "minTemperature": 60.0
        },
        {
            "id": 4,
            "localizedName": "Pinamar",
            "weatherType": "D",
            "createDate": "2023-04-27T05:29:42.759+00:00",
            "weatherDate": "2023-04-29T10:00:00.000+00:00",
            "dayDescriptions": "Intermittent clouds",
            "nightDescriptions": "Showers",
            "dayHasPrecipitation": false,
            "nightHasPrecipitation": true,
            "maxTemperature": 79.0,
            "minTemperature": 66.0
        },
        {
            "id": 5,
            "localizedName": "Pinamar",
            "weatherType": "D",
            "createDate": "2023-04-27T05:29:42.760+00:00",
            "weatherDate": "2023-04-30T10:00:00.000+00:00",
            "dayDescriptions": "Rain",
            "nightDescriptions": "Partly cloudy w/ showers",
            "dayHasPrecipitation": true,
            "nightHasPrecipitation": true,
            "maxTemperature": 71.0,
            "minTemperature": 44.0
        },
        {
            "id": 7,
            "localizedName": "Pinamar",
            "weatherType": "D",
            "createDate": "2023-04-27T05:37:43.216+00:00",
            "weatherDate": "2023-04-27T10:00:00.000+00:00",
            "dayDescriptions": "Intermittent clouds",
            "nightDescriptions": "Mostly clear",
            "dayHasPrecipitation": false,
            "nightHasPrecipitation": false,
            "maxTemperature": 71.0,
            "minTemperature": 55.0
        },
        {
            "id": 8,
            "localizedName": "Pinamar",
            "weatherType": "D",
            "createDate": "2023-04-27T05:37:43.239+00:00",
            "weatherDate": "2023-04-28T10:00:00.000+00:00",
            "dayDescriptions": "Mostly sunny",
            "nightDescriptions": "Mostly clear",
            "dayHasPrecipitation": false,
            "nightHasPrecipitation": false,
            "maxTemperature": 81.0,
            "minTemperature": 60.0
        },
        {
            "id": 9,
            "localizedName": "Pinamar",
            "weatherType": "D",
            "createDate": "2023-04-27T05:37:43.263+00:00",
            "weatherDate": "2023-04-29T10:00:00.000+00:00",
            "dayDescriptions": "Intermittent clouds",
            "nightDescriptions": "Showers",
            "dayHasPrecipitation": false,
            "nightHasPrecipitation": true,
            "maxTemperature": 79.0,
            "minTemperature": 66.0
        },
        {
            "id": 10,
            "localizedName": "Pinamar",
            "weatherType": "D",
            "createDate": "2023-04-27T05:37:43.287+00:00",
            "weatherDate": "2023-04-30T10:00:00.000+00:00",
            "dayDescriptions": "Rain",
            "nightDescriptions": "Partly cloudy w/ showers",
            "dayHasPrecipitation": true,
            "nightHasPrecipitation": true,
            "maxTemperature": 71.0,
            "minTemperature": 44.0
        }
    ]
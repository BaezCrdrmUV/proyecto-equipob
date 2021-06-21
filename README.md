#Proyecto SpotyMe TM
Este es un proyecto para la EE Desarrollo de sistemas en red y Desarrollo de Aplicaciones.
Funcionalidad: Este proyecto funciona como un clon de spotify
##Inicializar el servidor

El servidor contiene cuatro Microservicios y una API RESTful para comunicarse entre los servicios mediante peticiones HTTP entre los microservicios

Se debe tener instalado Docker en para poder crear los contenedores de los microservicios.

Para clonar el repositorio se debe clonar cada rama del repositorio para poder levantar los servicios mediante un docker-compose.
Se recomienda crear una carpeta llamada SpotyMe
SpotyMe es la carpeta donde estará todo el proyecto
Dentro de esta carpeta se crearan las siguientes 9 carpetas, cada una es una rama del repositorio, y por lo tanto debe contener los archivos de esas ramas:
├───main   ├───Cliente│   ├───ClienteMovil│   ├───db├   ├───MSAccount├   ├───MSPrivateLibrary├   ├───MSPublicLibrary├   ├───MSStreaming└───├───SpotyMeAPI
Cada carpeta debe corresponder a una rama del repositorio para coincidir con el sistema de archivos dentro del docker-compose.yaml, al clonar el repositorio principal, se debe realizar un clonado de cada rama para la creación correcta del servidor.

Para inicializar los servicios se debe estar dentro de la carpeta main donde se encuentra el archivo docker-compose.yml y se debe ejecutar el siguiente comando:

```bash
docker-compose up --build
```
Una vez creado los servicios se pueden ejecutar los clientes

# Foro Hub
![STATUS](https://img.shields.io/badge/STATUS-FINALIZADO-blue)

### Descripción
Foro Hub es una aplicación web de foro desarrollada con Spring Boot, diseñada para gestionar tópicos de discusión. Esta aplicación incluye funcionalidades CRUD (Crear, Leer, Actualizar, Eliminar) y utiliza JWT para autenticación y autorización.

### Herramientas utilizadas
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

### Requisitos

- Java 17
- Spring Boot versión 3.0.2 en adelante
- MySql: versión 8 en adelante
- Maven 3.6 o superior


### Funcionalidad
**Autenticación:**
Para autenticarte, envía una solicitud POST a /login con las credenciales del usuario. Esto devolverá un token JWT que debe incluirse en el encabezado de autorización para todas las solicitudes posteriores.


**Endpoints Principales:**
- POST /topicos: Crea un nuevo tópico.
- GET /topicos: Obtiene la lista de tópicos.
- PUT /topicos/{id}: Actualiza un tópico existente.
- DELETE /topicos/{id}: Elimina un tópico de la base de datos.
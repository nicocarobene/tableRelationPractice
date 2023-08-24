# Proyecto Biblioteca Rest

Este proyecto es una aplicación REST para administrar publicaciones y comentarios en una biblioteca virtual.

## Características

- Gestión de publicaciones y comentarios.
- Auditoría de fechas de creación y actualización.
- API RESTful para acceder a las funcionalidades.
- Paginación y ordenamiento de resultados.
- Validación de datos en las peticiones.

## Tecnologías Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven

## Configuración

1. Clona este repositorio en tu máquina local.
2. Importa el proyecto en tu IDE favorito como un proyecto Maven.
3. Configura las propiedades de la base de datos en `src/main/resources/application.properties`.
4. Ejecuta la aplicación.

## Uso

- Accede a la API en `http://localhost:8080/api`.

## Endpoints

- `GET /api/posts`: Obtener una lista de publicaciones.
- `POST /api/posts`: Crear una nueva publicación.
- `GET /api/comments`: Obtener una lista de comentarios.
- `POST /api/comments`: Crear un nuevo comentario.

## Contribuir

Si deseas contribuir a este proyecto, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una rama para tu nueva característica (`git checkout -b nueva-caracteristica`).
3. Realiza tus cambios y commitea (`git commit -m "Agregada nueva característica"`).
4. Haz push de tus cambios a tu repositorio fork (`git push origin nueva-caracteristica`).
5. Crea un Pull Request en este repositorio.

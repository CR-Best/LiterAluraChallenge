# LiterAlura - Catálogo de Libros

**Descripción del Proyecto:**

En este emocionante desafío de programación, aprenderás a construir tu propio catálogo de libros: *LiterAlura*. Este proyecto te permitirá interactuar con una API de libros, manipular datos en formato JSON, almacenarlos en una base de datos y permitir que los usuarios filtren y visualicen información sobre libros y autores de interés.

**Objetivo del Proyecto:**

El objetivo principal de este proyecto es desarrollar un Catálogo de Libros interactivo que permita a los usuarios realizar al menos 5 interacciones diferentes con la consola. Los libros serán buscados a través de una API específica, y la información obtenida será almacenada en una base de datos para su posterior consulta.

## Requisitos

- **Java 17** o superior.
- **IDE**: IntelliJ IDEA, Eclipse, o cualquier otra que soporte Java.
- **Maven** (para la gestión de dependencias).
- **Base de datos**: Puede ser local (H2, SQLite) o un sistema de base de datos como PostgreSQL.
- **Acceso a la API de libros**: (detalles de la API se proporcionarán más adelante).
- **Dependencias**: Ver el archivo `pom.xml` para las dependencias necesarias.

## Funcionalidades del Proyecto

1. **Buscar libros a través de una API**: Realiza solicitudes HTTP a una API de libros para obtener información relevante.
2. **Análisis de la respuesta JSON**: Procesa y maneja los datos JSON obtenidos de la API.
3. **Almacenamiento en base de datos**: Inserta y consulta libros y autores desde una base de datos.
4. **Interacción con el usuario**: Ofrece al usuario opciones para realizar búsquedas de libros, filtrar por autor o género, y mostrar resultados relevantes.
5. **Opciones de interacción**:
   - Buscar libros por título.
   - Buscar libros por autor.
   - Listar todos los libros disponibles.
   - Ver detalles de un libro seleccionado.
   - Filtrar libros por género.

## Pasos para Completar el Proyecto

### 1. Configuración del Ambiente Java

Para comenzar, asegúrate de tener Java 17 o superior instalado en tu máquina. Puedes descargarlo desde el sitio oficial de [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html).

Si usas **Maven**, también asegúrate de tenerlo correctamente instalado y configurado.

### 2. Creación del Proyecto

Crea un nuevo proyecto en tu IDE preferido. Si estás utilizando IntelliJ IDEA, puedes crear un nuevo proyecto de **Java con Maven** y configurar las dependencias necesarias en el archivo `pom.xml`.

### 3. Consumo de la API

Obtén una API pública de libros (puedes usar [Open Library API](https://openlibrary.org/developers/api) o cualquier otra disponible). Realiza solicitudes HTTP para obtener información sobre libros (título, autor, género, etc.). Utiliza una librería como `HttpClient` para realizar las solicitudes y procesar las respuestas.

### 4. Análisis de la Respuesta JSON

La respuesta de la API probablemente estará en formato JSON. Para manejar los datos JSON, puedes usar una librería como **Gson** o **Jackson** para convertir las respuestas en objetos de Java.

### 5. Inserción y Consulta en la Base de Datos

Configura una base de datos local (como H2) o utiliza una base de datos externa (como PostgreSQL) para almacenar los libros y sus detalles. Usa **JPA** (Java Persistence API) o **JDBC** para interactuar con la base de datos. Asegúrate de que los datos de libros y autores se inserten correctamente y que puedas consultarlos fácilmente.

### 6. Exhibición de Resultados a los Usuarios

Crea un menú interactivo en la consola que permita al usuario seleccionar diferentes opciones para interactuar con el catálogo de libros. Utiliza `Scanner` o cualquier otra librería de entrada para leer las selecciones del usuario y mostrar los resultados de manera clara.

## Estructura del Proyecto

```plaintext
src/
 ├── main/
 │   ├── java/
 │   │   └── com/
 │   │       └── literalura/
 │   │           ├── LiterAluraApplication.java
 │   │           ├── model/
 │   │           ├── service/
 │   │           └── controller/
 │   └── resources/
 │       └── application.properties
 └── test/
     ├── java/
     │   └── com/
     │       └── literalura/
     └── resources/

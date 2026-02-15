# 📚 LiterAlura - Catálogo de Libros

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Status](https://img.shields.io/badge/Status-Finalizado-brightgreen)

## 📝 Descripción

**LiterAlura** es una aplicación de consola desarrollada en Java con Spring Boot que permite buscar libros, autores y estadísticas desde la API de **Gutendex** (Project Gutenberg) y persistir los datos en una base de datos **PostgreSQL**.

Este proyecto es parte del desafío final de la especialización Backend del programa **ONE (Oracle Next Education)** en colaboración con **Alura**.

## ✨ Funcionalidades

La aplicación ofrece un menú interactivo con las siguientes opciones:

1.  **🔍 Buscar libro por título:** Consulta la API externa, muestra la ficha técnica y guarda el libro y el autor en la base de datos local.
2.  **📋 Listar libros registrados:** Muestra todos los libros que se han guardado en la base de datos.
3.  **🗣️ Listar libros por idioma:** Filtra los libros guardados (ES, EN, FR, PT, etc.).
4.  **✒️ Listar autores registrados:** Muestra la lista de autores con sus años de vida.
5.  **📅 Listar autores vivos en un año determinado:** Permite ingresar un año y muestra qué autores estaban vivos en ese momento (lógica compleja de fechas).
6.  **📊 Generar estadísticas:** Muestra el conteo de libros por idioma en la base de datos usando Streams.

## 🛠️ Tecnologías Utilizadas

* **Java 17:** Lenguaje de programación.
* **Spring Boot 3:** Framework para el desarrollo de la aplicación.
* **Spring Data JPA:** Para la persistencia y manejo de la base de datos.
* **PostgreSQL:** Motor de base de datos relacional.
* **Hibernate:** ORM para el mapeo de entidades.
* **Jackson:** Biblioteca para el parseo de JSON (consumo de API).
* **Maven:** Gestor de dependencias.
* **Gutendex API:** Fuente de datos externa.

## 🚀 Cómo ejecutar el proyecto

### Prerrequisitos
* Java 17 o superior.
* Maven.
* PostgreSQL instalado y ejecutándose.

### Pasos
1.  **Clonar el repositorio:**
    ```bash
    git clone https://github.com/adrianarodriguez23889/literAlura-Challenge.git
    ```

2.  **Configurar la Base de Datos:**
    En PostgreSQL, crea una base de datos llamada `literalura`.
    ```sql
    CREATE DATABASE literalura;
    ```

3.  **Variables de Entorno:**
    Configura tus credenciales de base de datos en el archivo `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
    spring.datasource.username=tu_usuario_postgres
    spring.datasource.password=tu_contraseña_postgres
    spring.jpa.hibernate.ddl-auto=update
    ```

4.  **Ejecutar:**
    Puedes correr el proyecto desde tu IDE (IntelliJ IDEA, Eclipse) ejecutando la clase `LiteraluraApplication.java` o mediante consola:
    ```bash
    mvn spring-boot:run
    ```
👤 Autor
Adriana B. Rodriguez

LinkedIn https://www.linkedin.com/in/adriana-beatriz-rodriguez/

GitHub https://github.com/adrianarodriguez23889

Desarrollado con ❤️ durante el programa ONE.

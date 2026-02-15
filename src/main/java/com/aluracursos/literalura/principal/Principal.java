package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.DatosLibro;
import com.aluracursos.literalura.model.DatosResultados;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;
import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.repository.AutorRepository;

import java.util.Scanner;
import java.util.List;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);

    // Repositorios
    private LibroRepository repositorio;
    private AutorRepository autorRepositorio;

    public Principal(LibroRepository repository, AutorRepository autorRepository) {
        this.repositorio = repository;
        this.autorRepositorio = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {

            var menu = """
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar libros por idioma
                    4 - Listar autores registrados
                    5 - Listar autores vivos en un determinado año
                    6 - Generar estadísticas
                                  
                    0 - Salir
                    """;
            System.out.println(menu);

            try {
                opcion = teclado.nextInt();
                teclado.nextLine();
            } catch (Exception e) {
                System.out.println("Por favor, ingrese un número válido.");
                teclado.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    buscarLibrosPorIdioma();
                    break;
                case 4:
                    mostrarAutoresRegistrados();
                    break;
                case 5:
                    buscarAutoresVivos();
                    break;
                case 6:
                    generarEstadisticas();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibroWeb() {
        DatosLibro datos = getDatosLibro();
        if (datos != null) {
            Libro libro = new Libro(datos);
            System.out.println("--- LIBRO ENCONTRADO EN API ---");

            if (!datos.autores().isEmpty()) {
                Autor autor = new Autor(datos.autores().get(0));
                autorRepositorio.save(autor);
                libro.setAutor(autor);
            }

            // Guardar Libro
            try {
                repositorio.save(libro);
                System.out.println(libro);
                System.out.println("--- ¡LIBRO GUARDADO EN BASE DE DATOS! ---");
            } catch (Exception e) {
                System.out.println("Error al guardar: Probablemente el libro ya existe.");
            }

        } else {
            System.out.println("El libro no existe en la base de datos de Gutenberg.");
        }
    }

    private void mostrarAutoresRegistrados() {
        List<Autor> autores = autorRepositorio.findAll();
        if (!autores.isEmpty()) {
            autores.forEach(System.out::println);
        } else {
            System.out.println("No hay autores registrados.");
        }
    }

    private void buscarAutoresVivos() {
        System.out.println("Ingresa el año vivo de autor(es) que deseas buscar:");
        try {
            var anio = teclado.nextInt();
            teclado.nextLine();

            List<Autor> autores = autorRepositorio.autoresVivosEnDeterminadoAnio(anio);
            if (!autores.isEmpty()) {
                autores.forEach(System.out::println);
            } else {
                System.out.println("No hay autores vivos en ese año registrados.");
            }
        } catch (Exception e) {
            System.out.println("Dato inválido, por favor ingresa un número de año.");
            teclado.nextLine();
        }
    }
    private void generarEstadisticas() {
        System.out.println("--- ESTADÍSTICAS DE LA BASE DE DATOS ---");

        var idiomas = List.of("es", "en", "fr", "pt");
        System.out.println("Cantidad de libros por idioma:");

        idiomas.stream().forEach(idioma -> {
            List<Libro> libros = repositorio.findByIdioma(idioma);
            System.out.println(idioma.toUpperCase() + ": " + libros.size() + " libros");
        });

        System.out.println("----------------------------------------");
    }

    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, DatosResultados.class);

        if (datosBusqueda.libros().isEmpty()) {
            return null;
        }

        return datosBusqueda.libros().get(0);
    }

    private void mostrarLibrosBuscados() {
        List<Libro> libros = repositorio.findAll();

        if (!libros.isEmpty()) {
            System.out.println("--- LIBROS REGISTRADOS ---");
            libros.forEach(System.out::println);
            System.out.println("--------------------------");
        } else {
            System.out.println("No hay libros registrados aún.");
        }
    }

    private void buscarLibrosPorIdioma() {
        System.out.println("Escribe el idioma por el que deseas buscar");
        System.out.println("Opciones: es (Español), en (Inglés), fr (Francés), pt (Portugués)");

        var idioma = teclado.nextLine();
        List<Libro> libros = repositorio.findByIdioma(idioma);

        if (!libros.isEmpty()) {
            System.out.println("--- LIBROS EN " + idioma.toUpperCase() + " ---");
            libros.forEach(System.out::println);
            System.out.println("--------------------------------");
        } else {
            System.out.println("No hay libros registrados en ese idioma.");
        }
    }
}
package LiterAluraChallenge.principal;

import LiterAluraChallenge.model.*;
import LiterAluraChallenge.repository.AutorRepository;
import LiterAluraChallenge.repository.LibrosRepository;
import LiterAluraChallenge.service.ConsumoAPI;
import LiterAluraChallenge.service.ConvierteDatos;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Principal {

    // URL base para obtener datos desde la API
    private static final String URL_BASE = "https://gutendex.com/books/";

    // Dependencias para la consulta de datos y la conversión de información
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();

    // Instancia del scanner para la lectura de entradas del usuario
    private final Scanner teclado = new Scanner(System.in);

    // Repositorios para acceder a los datos de libros y autores
    @Autowired
    private LibrosRepository librosRepository;

    @Autowired
    private AutorRepository autorRepository;

    // Método principal para mostrar el menú y gestionar las opciones seleccionadas
    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            // Definición del menú de opciones
            var menu = """
                    1 - Lista de todos los libros
                    2 - Búsqueda de libro por título
                    3 - Lista de todos los autores disponibles
                    4 - Buscar idiomas disponibles
                    5 - Autores vivos durante la fecha ingresada
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();  // Limpiar el buffer de entrada

            // Manejo de las opciones seleccionadas
            switch (opcion) {
                case 1 -> listarLibros();
                case 2 -> buscarLibroTitulo();
                case 3 -> listarAutores();
                case 4 -> buscarIdioma();
                case 5 -> listarAutoresVivosEnAño();
                case 0 -> System.out.println("Cerrando la aplicación...");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    // Método para listar todos los libros disponibles
    public void listarLibros() {
        // Obtener los datos de los libros desde la API
        var json = consumoAPI.obtenerDatos(URL_BASE);
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        System.out.println("Libros disponibles:");
        datosBusqueda.resultados().stream()
                .sorted(Comparator.comparing(DatosLibros::titulo)) // Ordenar por título
                .map(DatosLibros::titulo) // Mapear a solo los títulos de los libros
                .forEach(System.out::println); // Mostrar los títulos por consola
    }

    // Método para listar todos los autores disponibles
    public void listarAutores() {
        // Obtener los datos desde la API
        var json = consumoAPI.obtenerDatos(URL_BASE);
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        System.out.println("Autores disponibles:");
        datosBusqueda.resultados().stream()
                .flatMap(libro -> libro.autor().stream()) // Obtener stream de autores
                .map(DatosAutor::nombre) // Mapear a los nombres de los autores
                .distinct() // Eliminar autores duplicados
                .sorted() // Ordenar alfabéticamente
                .forEach(System.out::println); // Mostrar los nombres de autores
    }

    // Método para buscar un libro por título
    public void buscarLibroTitulo() {
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        // Buscar el libro por su título
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase())) // Filtrar por título
                .findFirst(); // Obtener el primer libro que coincida

        if (libroBuscado.isPresent()) {
            var libro = libroBuscado.get();
            DatosAutor primerAutor = libro.autor().stream().findFirst().orElse(null);

            if (primerAutor != null) {
                // Consultar el autor en la base de datos
                Autor autor = autorRepository.findByNombre(primerAutor.nombre());

                // Si el autor no existe, crearlo
                if (autor == null) {
                    autor = new Autor();
                    autor.setNombre(primerAutor.nombre());
                    autor.setFechaDeNacimiento(primerAutor.fechaDeNacimiento());
                    autor.setFechaDeFallecimiento(primerAutor.fechaDeFallecimiento());
                    autor.setLibros(new ArrayList<>()); // Inicializar la lista de libros
                }

                // Crear un nuevo libro y asignarlo al autor
                Libros libroNuevo = new Libros();
                libroNuevo.setTitulo(libro.titulo());
                libroNuevo.setAutores(autor);
                libroNuevo.setIdiomas(String.join(", ", libro.idiomas()));
                libroNuevo.setNumeroDeDescargas(libro.numeroDeDescargas());

                // Agregar el libro al autor y guardar ambos
                autor.getLibros().add(libroNuevo);
                autorRepository.save(autor);

                // Mostrar los detalles del libro encontrado
                System.out.println("Libro encontrado:");
                System.out.println("Título: " + libroNuevo.getTitulo());
                System.out.println("Idiomas: " + libroNuevo.getIdiomas());
                System.out.println("Número de descargas: " + libroNuevo.getNumeroDeDescargas());
                System.out.println("Autor: " + autor.getNombre());
                System.out.println("Fecha de nacimiento: " + autor.getFechaDeNacimiento());
                System.out.println("Fecha de fallecimiento: " + autor.getFechaDeFallecimiento());
            } else {
                System.out.println("No se encontró autor para el libro");
            }
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    // Método para buscar libros por idioma
    public void buscarIdioma() {
        System.out.println("Ingrese el idioma que desea buscar:");
        var idioma = teclado.nextLine();
        List<Libros> libros = librosRepository.findByIdioma(idioma);

        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma ingresado.");
        } else {
            System.out.println("Libros encontrados en el idioma " + idioma + ":");
            libros.forEach(libro -> {
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Idiomas: " + libro.getIdiomas());
                System.out.println("Número de descargas: " + libro.getNumeroDeDescargas());
            });
        }
    }

    // Método para listar autores vivos en un año específico
    public void listarAutoresVivosEnAño() {
        System.out.println("Ingrese el año para listar los autores vivos:");
        var año = teclado.nextLine();
        List<Autor> autores = autorRepository.findAutoresVivosEnAño(año);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos durante el año ingresado.");
        } else {
            autores.forEach(System.out::println);
        }
    }
}

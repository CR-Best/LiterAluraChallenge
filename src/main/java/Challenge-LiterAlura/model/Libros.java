package LiterAluraChallenge.model;

import jakarta.persistence.*;

// Clase que representa un libro, utilizando anotaciones JPA para persistencia
@Entity
@lombok.Data // Lombok genera automáticamente getters, setters, toString, equals y hashCode
@lombok.AllArgsConstructor // Constructor con parámetros
@lombok.NoArgsConstructor // Constructor sin parámetros
public class Libros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único del libro

    private String titulo; // Título del libro

    @ManyToOne
    @JoinColumn(name = "autor_id") // Relación con la entidad Autor
    private Autor autores; // Relación de muchos libros a un autor

    private String idiomas; // Idiomas en los que está disponible el libro

    private Double numeroDeDescargas; // Número de descargas del libro

    // Método toString personalizado para mostrar la información del libro
    @Override
    public String toString() {
        return "Libros{"
                + "id=" + id
                + ", titulo='" + titulo + '\'' // Muestra el título del libro
                + ", idiomas='" + idiomas + '\'' // Muestra los idiomas
                + ", numeroDeDescargas=" + numeroDeDescargas // Muestra el número de descargas
                + '}';
    }
}

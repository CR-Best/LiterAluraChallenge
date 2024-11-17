package LiterAluraChallenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignora propiedades adicionales en el JSON
public record DatosLibros(
        @JsonAlias("title") String titulo, // Título del libro
        @JsonAlias("authors") List<DatosAutor> autor, // Lista de autores
        @JsonAlias("languages") List<String> idiomas, // Idiomas en los que está disponible el libro
        @JsonAlias("download_count") Double numeroDeDescargas // Número de descargas del libro
) {
}

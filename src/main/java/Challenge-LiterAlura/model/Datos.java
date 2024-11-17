package LiterAluraChallenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

// Clase para mapear la respuesta JSON de la API relacionada con los libros
@JsonIgnoreProperties(ignoreUnknown = true) // Ignora propiedades desconocidas al deserializar JSON
public record Datos(
        @JsonAlias("results") List<DatosLibros> resultados // Alias para el campo "results" en la respuesta JSON
) {
}

package LiterAluraChallenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignora propiedades no mapeadas al deserializar
public record DatosAutor(
        @JsonAlias("name") String nombre, // Mapea el nombre del autor
        @JsonAlias("birth_year") String fechaDeNacimiento, // Mapea el año de nacimiento
        @JsonAlias("death_year") String fechaDeFallecimiento // Mapea el año de fallecimiento
) {
}

package LiterAluraChallenge.model;

import jakarta.persistence.*;
import java.util.List;

// Clase que representa un Autor, usando anotaciones de JPA para la persistencia en base de datos
@Entity
@lombok.Data // Genera los métodos getter, setter, toString, equals y hashCode automáticamente
@lombok.AllArgsConstructor // Constructor con todos los parámetros
@lombok.NoArgsConstructor // Constructor sin parámetros (por defecto)
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único para cada autor

    private String nombre; // Nombre del autor

    @Column(name = "fecha_de_nacimiento")
    private String fechaDeNacimiento; // Fecha de nacimiento del autor

    @Column(name = "fecha_de_fallecimiento")
    private String fechaDeFallecimiento; // Fecha de fallecimiento del autor

    @OneToMany(mappedBy = "autores", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libros> libros; // Relación uno a muchos con la entidad Libros

    // Constructor personalizado
    public Autor(String nombre, String fechaDeNacimiento, String fechaDeFallecimiento) {
        this.nombre = nombre;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    // Método toString para mostrar la información del autor de forma legible
    @Override
    public String toString() {
        return "\nAutor:\n"
                + "id = " + id + "\n"
                + "nombre = " + nombre + "\n"
                + "fechaDeNacimiento = " + fechaDeNacimiento + "\n"
                + "fechaDeFallecimiento = " + fechaDeFallecimiento
                + "\n";
    }
}

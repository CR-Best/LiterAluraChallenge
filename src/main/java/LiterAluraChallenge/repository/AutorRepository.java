package LiterAluraChallenge.repository;

import LiterAluraChallenge.model.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Autor save(Autor autor); // Guarda un autor

    Autor findByNombre(String nombre); // Encuentra un autor por su nombre

    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento = :años")
    List<Autor> findAutoresVivosEnAño(String años); // Busca autores nacidos en un año específico
}

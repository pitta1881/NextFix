package ar.dev.patriciopittavino.nextfix.repository;

import ar.dev.patriciopittavino.nextfix.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    Optional<Pelicula> findByGenero(String genero);

    @Query("SELECT p FROM Pelicula p ORDER BY p.titulo ASC")
    List<Pelicula> findAllByOrderByTituloIgnoreCaseAsc();

    void deleteByGenero(String genero);

}

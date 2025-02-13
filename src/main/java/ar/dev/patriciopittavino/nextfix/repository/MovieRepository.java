package ar.dev.patriciopittavino.nextfix.repository;

import ar.dev.patriciopittavino.nextfix.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByGenre(String genre);

    @Query("SELECT p FROM Movie p ORDER BY p.title ASC")
    List<Movie> findAllByOrderByTitleIgnoreCaseAsc();

    void deleteByGenre(String genre);

}

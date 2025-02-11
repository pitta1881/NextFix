package ar.dev.patriciopittavino.nextfix.repository;

import ar.dev.patriciopittavino.nextfix.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
}

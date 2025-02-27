package ar.dev.patriciopittavino.nextfix.repository;

import ar.dev.patriciopittavino.nextfix.model.Director;
import ar.dev.patriciopittavino.nextfix.model.UserCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserCustom, Long> {

    UserCustom findByUsername(String username);
    UserCustom findByDirector(Director director);
    List<UserCustom> findByDirectorIsNotNull();
}

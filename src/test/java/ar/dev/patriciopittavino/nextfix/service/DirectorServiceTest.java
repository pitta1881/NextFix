package ar.dev.patriciopittavino.nextfix.service;

import ar.dev.patriciopittavino.nextfix.BaseTest;
import ar.dev.patriciopittavino.nextfix.model.Director;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
class DirectorServiceTest extends BaseTest {

    private final DirectorService directorService;
    private Director savedDirector;

    @BeforeEach
    void setup() {
        Director director = new Director();
        director.setNationality("Argentina");
        director.setEmail("director1@mail.com");
        director.setBirthdate(LocalDate.of(1980, 1, 1));
        savedDirector = directorService.saveDirector(director);
    }

    @Test
    @Order(1)
    void saveDirector() {
        assertNotNull(savedDirector.getId());
        assertEquals("Argentina", savedDirector.getNationality());
        assertEquals("director1@mail.com", savedDirector.getEmail());
        assertEquals(LocalDate.of(1980, 1, 1), savedDirector.getBirthdate());
    }

    @Test
    @Order(2)
    void getDirectorById() {
        Director director = directorService.getDirectorById(savedDirector.getId());
        assertEquals(savedDirector.getId(), director.getId());
        assertEquals(savedDirector.getNationality(), director.getNationality());
        assertEquals(savedDirector.getEmail(), director.getEmail());
        assertEquals(savedDirector.getBirthdate(), director.getBirthdate());
    }

    @Test
    @Order(3)
    void getAllDirectors() {
        assertFalse(directorService.getAllDirectors().isEmpty());
    }

    @Test
    @Order(4)
    void deleteDirector() {
        directorService.deleteDirector(savedDirector.getId());
        assertThrows(RuntimeException.class, () -> directorService.getDirectorById(savedDirector.getId()));
    }
}
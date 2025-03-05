package ar.dev.patriciopittavino.nextfix.service;

import ar.dev.patriciopittavino.nextfix.BaseTest;
import ar.dev.patriciopittavino.nextfix.model.Director;
import ar.dev.patriciopittavino.nextfix.model.Movie;
import ar.dev.patriciopittavino.nextfix.model.Platform;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class MovieServiceTest extends BaseTest {

    private final MovieService movieService;
    private final DirectorService directorService;
    private final PlatformService platformService;

    private Movie tempMovie;
    private Director savedDirector;
    private Platform savedPlatform;

    @BeforeEach
    void setup() {
        Director director = new Director();
        director.setNationality("Argentina");
        director.setEmail("director1@mail.com");
        director.setBirthdate(LocalDate.of(1980, 1, 1));
        savedDirector = directorService.saveDirector(director);

        Platform platform = new Platform();
        platform.setName("Netflix");
        platform.setCurrency("USD");
        platform.setPrice(new BigDecimal("10.99"));
        platform.setUrl("https://www.netflix.com");
        savedPlatform = platformService.savePlatform(platform);

        tempMovie = new Movie();
        tempMovie.setTitle("The Shawshank Redemption");
        tempMovie.setGenre("Drama");
        tempMovie.setReleaseDate(LocalDate.of(1994, 10, 14));
    }

    @Test
    void saveMovieWithoutPlatforms() {
        Movie savedMovie = movieService.saveMovie(tempMovie, savedDirector.getId(), null);
        assertNotNull(savedMovie.getId());
        assertEquals("The Shawshank Redemption", savedMovie.getTitle());
        assertEquals("Drama", savedMovie.getGenre());
        assertEquals(LocalDate.of(1994, 10, 14), savedMovie.getReleaseDate());
        assertEquals(savedDirector.getId(), savedMovie.getDirector().getId());
        assertTrue(savedMovie.getAvalaiblePlatforms().isEmpty());
    }

    @Test
    void saveMovieWithPlatforms() {
        List<Long> idPlatforms = new ArrayList<>();
        idPlatforms.add(savedPlatform.getId());
        Movie savedMovie = movieService.saveMovie(tempMovie, savedDirector.getId(), idPlatforms);
        assertNotNull(savedMovie.getId());
        assertEquals("The Shawshank Redemption", savedMovie.getTitle());
        assertEquals("Drama", savedMovie.getGenre());
        assertEquals(LocalDate.of(1994, 10, 14), savedMovie.getReleaseDate());
        assertEquals(savedDirector.getId(), savedMovie.getDirector().getId());
        assertFalse(savedMovie.getAvalaiblePlatforms().isEmpty());
        assertEquals(savedPlatform.getId(), savedMovie.getAvalaiblePlatforms().getFirst().getId());
    }

    @Test
    void listAllMovies() {
        assertFalse(movieService.getAllMovies().isEmpty());
    }
}

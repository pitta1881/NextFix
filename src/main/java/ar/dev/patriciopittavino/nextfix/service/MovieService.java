package ar.dev.patriciopittavino.nextfix.service;

import ar.dev.patriciopittavino.nextfix.model.Director;
import ar.dev.patriciopittavino.nextfix.model.Movie;
import ar.dev.patriciopittavino.nextfix.repository.DirectorRepository;
import ar.dev.patriciopittavino.nextfix.repository.MovieRepository;
import ar.dev.patriciopittavino.nextfix.repository.PlatformRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieService {

    private MovieRepository movieRepository;
    private DirectorRepository directorRepository;
    private PlatformRepository platformRepository;

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAllByOrderByTitleIgnoreCaseAsc();
    }

    public Movie saveMovie(Movie movie, Long idDirector, List<Long> idPlatforms) {
        movie.setDirector(directorRepository.findById(idDirector).orElseThrow(() -> new RuntimeException("Director not found")));
        movie.setAvalaiblePlatforms(platformRepository.findAllById(idPlatforms));
        return movieRepository.save(movie);
    }

    public void updateMovie(Long id, Movie updatedMovie, Long idDirector, List<Long> idPlatforms) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        updatedMovie.setDirector(directorRepository.findById(idDirector).orElseThrow(() -> new RuntimeException("Director not found")));
        updatedMovie.setAvalaiblePlatforms(platformRepository.findAllById(idPlatforms));
        Movie existingMovie = buildMovie(updatedMovie, movieOptional);
        movieRepository.save(existingMovie);
    }

    private Movie buildMovie(Movie updatedMovie, Optional<Movie> movieOptional) {

        Movie.MovieBuilder movieBuilder = Movie.builder();
        movieOptional.ifPresent(movie -> {
            movieBuilder.id(movie.getId());
            movieBuilder.title(updatedMovie.getTitle());
            movieBuilder.genre(updatedMovie.getGenre());
            movieBuilder.releaseDate(updatedMovie.getReleaseDate());
            movieBuilder.director(updatedMovie.getDirector());
            movieBuilder.avalaiblePlatforms(updatedMovie.getAvalaiblePlatforms());
        });
        return movieBuilder.build();
    }

    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }
}

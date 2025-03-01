package ar.dev.patriciopittavino.nextfix.controller.view;

import ar.dev.patriciopittavino.nextfix.model.Movie;
import ar.dev.patriciopittavino.nextfix.service.DirectorService;
import ar.dev.patriciopittavino.nextfix.service.MovieService;
import ar.dev.patriciopittavino.nextfix.service.PlatformService;
import ar.dev.patriciopittavino.nextfix.service.UserCustomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class MovieController {

    private final PlatformService platformService;
    private final MovieService movieService;
    private final DirectorService directorService;
    private final UserCustomService userService;

    @GetMapping("/movies")
    public String getMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movies/moviesList";
    }

    @GetMapping("/movies/new")
    public String newPlatform(Model model) {
        model.addAttribute("usersWithDirector", userService.listUsersRegisteredWithDirectors());
        model.addAttribute("platforms", platformService.getAllPlatforms());
        model.addAttribute("movie", new Movie());
        return "movies/addMovieForm";
    }

    @PostMapping("/movies")
    public String savePlatform(@ModelAttribute Movie movie, @RequestParam Long directorId, @RequestParam(required = false)List<Long> platformIds) {
        movieService.saveMovie(movie, directorId, platformIds);
        return "redirect:/movies";
    }

    @GetMapping("/movies/edit/{id}")
    public String editMovie(@PathVariable Long id, Model model) {
        model.addAttribute("usersWithDirector", userService.listUsersRegisteredWithDirectors());
        model.addAttribute("platforms", platformService.getAllPlatforms());
        model.addAttribute("movie", movieService.getMovieById(id));
        return "movies/editMovieForm";
    }

    @PostMapping("/movies/{id}")
    public String updateMovie(@PathVariable Long id, @ModelAttribute Movie movie, @RequestParam Long directorId, @RequestParam(required = false)List<Long> platformIds) {
        movieService.updateMovie(id, movie, directorId, platformIds);
        return "redirect:/movies";
    }

//    no se puede hacer DeleteMapping en una view, solo en rest
    @GetMapping("/movies/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return "redirect:/movies";
    }
}

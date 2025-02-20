package ar.dev.patriciopittavino.nextfix.controller.view;

import ar.dev.patriciopittavino.nextfix.service.DirectorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping("/directors")
    public String getDirectors(Model model) {
        model.addAttribute("directors", directorService.getAllDirectors());
        return "directors/directorsList";
    }
}

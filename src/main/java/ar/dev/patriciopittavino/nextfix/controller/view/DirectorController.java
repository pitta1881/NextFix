package ar.dev.patriciopittavino.nextfix.controller.view;

import ar.dev.patriciopittavino.nextfix.model.Director;
import ar.dev.patriciopittavino.nextfix.service.DirectorService;
import ar.dev.patriciopittavino.nextfix.service.UserCustomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DirectorController {

    private final DirectorService directorService;
    private final UserCustomService userCustomService;

    @GetMapping("/directors")
    public String getDirectors(Model model) {
        model.addAttribute("directors", directorService.getAllDirectors());
        model.addAttribute("userService", userCustomService);
        return "directors/directorsList";
    }
}

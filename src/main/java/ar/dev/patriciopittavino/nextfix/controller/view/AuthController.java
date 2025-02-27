package ar.dev.patriciopittavino.nextfix.controller.view;

import ar.dev.patriciopittavino.nextfix.model.UserCustom;
import ar.dev.patriciopittavino.nextfix.service.UserCustomService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final UserCustomService userDetailsService;

    @GetMapping("/")
    public String redirectToHome() {
        log.info("--> GET /");
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login() {
        log.info("--> GET /login");
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        log.info("--> GET /register");
        model.addAttribute("user", new UserCustom());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(UserCustom user) {
        log.info("--> POST /register");
        userDetailsService.save(user);
        return "redirect:/login";
    }

}

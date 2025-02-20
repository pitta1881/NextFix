package ar.dev.patriciopittavino.nextfix.controller.view;

import ar.dev.patriciopittavino.nextfix.model.Platform;
import ar.dev.patriciopittavino.nextfix.service.PlatformService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class PlatformController {

    private final PlatformService platformService;

    @GetMapping("/platforms")
    public String getPlatforms(Model model) {
        model.addAttribute("platforms", platformService.getAllPlatforms());
        return "platforms/platformsList";
    }

    @GetMapping("/platforms/new")
    public String newPlatform(Model model) {
        model.addAttribute("platform", new Platform());
        return "platforms/platformForm";
    }

    @PostMapping("/platforms")
    public String savePlatform(@ModelAttribute Platform platform) {
        platformService.savePlatform(platform);
        return "redirect:/platforms";
    }
}

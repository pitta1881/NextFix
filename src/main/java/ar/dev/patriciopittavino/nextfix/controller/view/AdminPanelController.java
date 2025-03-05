package ar.dev.patriciopittavino.nextfix.controller.view;
import ar.dev.patriciopittavino.nextfix.model.Director;
import ar.dev.patriciopittavino.nextfix.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@AllArgsConstructor
public class AdminPanelController {

    private final UserCustomService userCustomService;
    private final DirectorService directorService;

    @GetMapping("/backoffice")
    public String backofficeRoot() {
        return "redirect:/backoffice/rolesManager";
    }

    @GetMapping("/backoffice/rolesManager")
    public String roleManager(Model model) {
        model.addAttribute("users", userCustomService.listUsersRegistered());
        return "backoffice/rolesManager/roleManager";
    }

    @GetMapping("/backoffice/rolesManager/updateUserRole/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userCustomService.getUserById(id));

        return "backoffice/rolesManager/updateUserRoleForm";
    }

    @PostMapping("/backoffice/rolesManager/updateUserRole/{id}")
    public String updateUser(@PathVariable Long id, @RequestParam(required = false) String role, @RequestParam(required = false) String nationality, @RequestParam(required = false) String email, @RequestParam(required = false) LocalDate birthdate) {
        if (role.equals("ROLE_DIRECTOR") && nationality != null && email !=null) {
            Director newDirector = new Director();
            newDirector.setNationality(nationality);
            newDirector.setEmail(email);
            newDirector.setBirthdate(birthdate);
            newDirector.setUser(userCustomService.getUserById(id));

            Director savedDirector = directorService.saveDirector(newDirector);
            userCustomService.updateUserRoleDirector(id, savedDirector);
        }
        else {
            userCustomService.updateUserRole(id, role);
        }
        return "redirect:/backoffice/rolesManager";
    }

    @GetMapping("/backoffice/rolesManager/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userCustomService.deleteUser(id);
        return "redirect:/backoffice/rolesManager";
    }
}
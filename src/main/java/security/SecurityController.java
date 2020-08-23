package security;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/login-form")
    public String login() {
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            return "redirect:/home";
        }
        return "login-page";
    }

    @GetMapping("/access-denied")
    public String accessPage() {
        return "access-page";
    }

}

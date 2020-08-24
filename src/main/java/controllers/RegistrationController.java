package controllers;

import configs.AppConfig;
import configs.HibernateConfig;
import models.security_models.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import security.service.UserService;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/registration")
@ComponentScan(basePackageClasses = {AppConfig.class, HibernateConfig.class})
@Transactional
public class RegistrationController {


    @GetMapping
    public String registration(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "security/registration";
    }

    @PostMapping
    public String registrationAction(@ModelAttribute("user") User user) {

        return "security/login_page";
    }

}

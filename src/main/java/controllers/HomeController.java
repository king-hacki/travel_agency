package controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @GetMapping({"/home", "/"})
    public String homeIndex(ModelMap modelMap) {
        log.info("home index controller ran");
        modelMap.addAttribute("message", "Welcome to Travel Agency");
        return "../../index";
    }
}

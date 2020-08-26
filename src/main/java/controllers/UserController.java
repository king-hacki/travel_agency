package controllers;

import configs.AppConfig;
import configs.HibernateConfig;
import dao.UserDao;
import exceptions.UserNotFoundException;
import models.Rent;
import models.security_models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

import java.util.Set;

import static java.lang.String.format;

@Controller
@ComponentScan(basePackageClasses = {AppConfig.class, HibernateConfig.class})
@Transactional
public class UserController {

    private UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/user/rents")
    public String userRents(ModelMap modelMap) {
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails)
            email = ((UserDetails) principal).getUsername();
        else
            email = principal.toString();
        User userEntity = userDao.getUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(format("User with email: %s not found", email)));
        Set<Rent> userRents = userEntity.getRents();
        modelMap.addAttribute("user", userEntity);
        modelMap.addAttribute("userRents", userRents);
        System.out.println("userRents = " + userRents);
        return "manager_action/user_rents";
    }


}

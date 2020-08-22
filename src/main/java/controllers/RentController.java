package controllers;

import configs.AppConfig;
import configs.HibernateConfig;
import models.Rent;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import services.RentService;
import services.UserService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Set;

@Controller
@RequestMapping("/rent")
@ComponentScan(basePackageClasses = {AppConfig.class, HibernateConfig.class})
@Transactional
public class RentController {

    private RentService rentService;
    private UserService userService;

    @Autowired
    public RentController(RentService rentService, UserService userService) {
        this.rentService = rentService;
        this.userService = userService;
    }

    @PostMapping("/book")
    public String bookRoom(@RequestParam("room_id") long roomId,
                           @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                           @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
                           ModelMap modelMap) {
        Rent rentEntity = rentService.bookRoom(roomId, start, end);
        modelMap.addAttribute("rentEntity", rentEntity);
        System.out.println("rentEntity = " + rentEntity);
        return "rent_registrate";
    }

    @GetMapping("/{userId}")
    public String userRents(@PathVariable long userId, ModelMap modelMap) {
        User userEntity = userService.getUserById(userId);
        Set<Rent> userRents = userEntity.getRents();
        modelMap.addAttribute("userRents", userRents);
        modelMap.addAttribute("user", userEntity);
        return "user_rents";
    }

}

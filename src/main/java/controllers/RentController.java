package controllers;

import configs.AppConfig;
import configs.HibernateConfig;
import models.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.RentService;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Controller
@RequestMapping("/rent")
@ComponentScan(basePackageClasses = {AppConfig.class, HibernateConfig.class})
@Transactional
public class RentController {

    private RentService rentService;

    @Autowired
    public RentController(RentService rentService) {
        this.rentService = rentService;
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

}

package controllers;


import configs.AppConfig;
import configs.HibernateConfig;
import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import models.security_models.User;
import services.CountryService;
import services.HotelService;
import services.RoomService;
import security.service.UserService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user_manager")
@ComponentScan(basePackageClasses = {AppConfig.class, HibernateConfig.class})
@Transactional
public class ManagerController {

    private UserService userService;
    private CountryService countryService;
    private HotelService hotelService;
    private RoomService roomService;

    @Autowired
    public ManagerController(UserService userService,
                             CountryService countryService,
                             HotelService hotelService,
                             RoomService roomService) {
        this.userService = userService;
        this.countryService = countryService;
        this.hotelService = hotelService;
        this.roomService = roomService;
    }

    @GetMapping("/action")
    public String managerDashboard(ModelMap modelMap) {
        return "manager_action/manager_dashboard";
    }

    @GetMapping("/create_country")
    public String createCountry(ModelMap modelMap) {
        modelMap.addAttribute("country", new Country());
        return "manager_action/create_country";
    }

    @PostMapping("/create_country")
    public String createCountryAction(@Valid @ModelAttribute("country") Country country,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "manager_action/create_country";
        System.out.println("country name = " + country.getName());
        countryService.createCountry(country);
        return "redirect:/country/list";
    }

    @GetMapping("/create_hotel")
    public String createHotel(@RequestParam("name") String countryName,
                              @RequestParam("countryId") long countryId,
                              ModelMap modelMap) {
        modelMap.addAttribute("countryName", countryName);
        modelMap.addAttribute("countryId", countryId);
        modelMap.addAttribute("hotel", new Hotel());
        return "manager_action/create_hotel";
    }

    @PostMapping("/create_hotel/{countryId}")
    public String createHotelAction(@PathVariable long countryId,
                                    @Valid @ModelAttribute("hotel") Hotel hotel,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "manager_action/create_hotel";
        System.out.println("hotel name = " + hotel.getName());
        hotelService.createNewHotel(hotel, countryId);
        return "redirect:http://localhost:8080/hotel/{countryId}";
    }

    @GetMapping("/create_room")
    public String createRoom(@RequestParam("hotelId") long hotelId,
                             ModelMap modelMap) {
        modelMap.addAttribute("hotelId", hotelId);
        modelMap.addAttribute("room", new Room());
        return "manager_action/create_room";
    }

    @PostMapping("/create_room/{hotelId}")
    public String createRoomAction(@PathVariable long hotelId,
                                    @Valid @ModelAttribute("room") Room room,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "manager_action/create_room";
        System.out.println("room name = " + room.getNumber());
        roomService.createNewRoom(room, hotelId);
        return "redirect:http://localhost:8080/room/all/{hotelId}";
    }

    @GetMapping("/all_users")
    public String allUsers(ModelMap modelMap) {
        List<User> allUsers = userService.allUsers();
        modelMap.addAttribute("allUsers", allUsers);
        return "manager_action/all_users";
    }

    @GetMapping("/all_users/{userId}")
    public String userRent(@PathVariable long userId,
                           ModelMap modelMap) {
        User userEntity = userService.getUserById(userId);
        Set<Rent> userRents = userEntity.getRents();
        modelMap.addAttribute("user", userEntity);
        modelMap.addAttribute("userRents", userRents);
        System.out.println("userRents = " + userRents);
        return "manager_action/user_rents";
    }

}

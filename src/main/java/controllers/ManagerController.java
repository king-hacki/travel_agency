package controllers;


import configs.AppConfig;
import configs.HibernateConfig;
import models.Country;
import models.Hotel;
import models.Room;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.CountryService;
import services.HotelService;
import services.RoomService;
import services.UserService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

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
        //  TODO custom exception
        if (bindingResult.hasErrors())
            throw new IllegalArgumentException("Create country exception");
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
        //  TODO custom exception
        if (bindingResult.hasErrors())
            throw new IllegalArgumentException("Create hotel exception");
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
        //  TODO custom exception
        if (bindingResult.hasErrors())
            throw new IllegalArgumentException("Create room exception");
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



}

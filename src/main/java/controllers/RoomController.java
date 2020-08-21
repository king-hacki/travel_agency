package controllers;

import configs.AppConfig;
import configs.HibernateConfig;
import models.Hotel;
import models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.HotelService;
import services.RoomService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/room")
@ComponentScan(basePackageClasses = {AppConfig.class, HibernateConfig.class})
@Transactional
public class RoomController {

    private RoomService roomService;
    private HotelService hotelService;

    @Autowired
    public RoomController(RoomService roomService, HotelService hotelService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
    }

    @PostMapping("/{hotelId}")
    public String findRooms(@PathVariable long hotelId,
                            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
                            ModelMap modelMap) {
        Hotel hotelEntity = hotelService.getById(hotelId);
        List<Room> availableRooms = roomService.findAvailableRoomsByPeriod(hotelId, start, end);
        modelMap.addAttribute("availableRooms", availableRooms);
        modelMap.addAttribute("start", start);
        modelMap.addAttribute("end", end);
        modelMap.addAttribute("hotel", hotelEntity);
        System.out.println("availableRooms = " + availableRooms);
        return "room_list";
    }

}

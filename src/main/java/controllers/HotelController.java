package controllers;

import configs.AppConfig;
import configs.HibernateConfig;
import lombok.extern.slf4j.Slf4j;
import models.Country;
import models.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import services.CountryService;
import services.HotelService;

import javax.transaction.Transactional;
import java.util.Set;

@Controller
@RequestMapping("/hotel")
@ComponentScan(basePackageClasses = {AppConfig.class, HibernateConfig.class})
@Transactional
public class HotelController {

    private HotelService hotelService;
    private CountryService countryService;

    @Autowired
    public HotelController(HotelService hotelService, CountryService countryService) {
        this.hotelService = hotelService;
        this.countryService = countryService;
    }

    @GetMapping("/{countryId}")
    public String getHotelsByCountry(@PathVariable long countryId,
                                     ModelMap modelMap) {
        Set<Hotel> hotelsFromCountry = hotelService.getHotelsFromCountry(countryId);
        Country countryEntity = countryService.getById(countryId);
        modelMap.addAttribute("hotels", hotelsFromCountry);
        modelMap.addAttribute("country", countryEntity);
        System.out.println(countryEntity);
        System.out.println(hotelsFromCountry);
        return "hotel_list";
    }

}

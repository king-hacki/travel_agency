package controllers;

import configs.AppConfig;
import configs.HibernateConfig;
import models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import services.CountryService;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/country")
@ComponentScan(basePackageClasses = {AppConfig.class, HibernateConfig.class})
public class CountryController {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/list")
    public String countryList(ModelMap modelMap) {
        List<Country> allCountries = countryService.getAllCountries();
        modelMap.addAttribute("allCountries", allCountries);
        return "country_list";
    }
}

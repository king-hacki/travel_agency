package exceptions.handler;


import configs.AppConfig;
import configs.HibernateConfig;
import exceptions.BadDateException;
import exceptions.CountryNotExistException;
import exceptions.HotelNotExistException;
import exceptions.UserNotFoundByIdException;
import lombok.extern.slf4j.Slf4j;
import models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import services.CountryService;
import services.HotelService;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@ControllerAdvice
@Slf4j
@Transactional
@ComponentScan(basePackageClasses = {AppConfig.class, HibernateConfig.class})
public class CustomExceptionHandler {

    private CountryService countryService;
    private HotelService hotelService;

    @Autowired
    public CustomExceptionHandler(CountryService countryService, HotelService hotelService) {
        this.hotelService = hotelService;
        this.countryService = countryService;
    }


    @ExceptionHandler({CountryNotExistException.class, HotelNotExistException.class})
    public String handleCountryNotExist(HttpServletRequest request, Exception exception) {
        log.error(exception.getMessage());
        List<Country> allCountries = countryService.getAllCountries();
        request.setAttribute("allCountries", allCountries);
        request.setAttribute("error", exception.getMessage());
        return "country_list";
    }

    @ExceptionHandler(BadDateException.class)
    private String handleBadDate(HttpServletRequest request, Exception exception) {
        log.error(exception.getMessage());
        request.setAttribute("error", exception.getMessage());
        return "hotel_list";
    }

    @ExceptionHandler(UserNotFoundByIdException.class)
    private String handleUserNotFoundById(HttpServletRequest request, Exception exception) {
        log.error(exception.getMessage());
        request.setAttribute("error", exception.getMessage());
        return "exceptions/user_not_found";
    }
}

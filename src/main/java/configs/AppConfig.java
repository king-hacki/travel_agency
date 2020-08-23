package configs;


import dao.*;
import dao.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import security.service.UserService;
import security.service.impl.UserServiceImpl;
import services.*;
import services.impl.*;

@Configuration
@ComponentScan(basePackages = {"dao", "services", "controllers", "security"})
public class AppConfig {

    @Bean
    @Autowired
    public HotelDao hotelDao(CountryDao countryDao) {
        return new HotelDaoImpl(countryDao);
    }

    @Bean
    public CountryDao countryDao() {
        return new CountryDaoImpl();
    }

    @Bean
    public RoomDao roomDao() {
        return new RoomDaoImpl();
    }

    @Bean
    public RentDao rentDao() {
        return new RentDaoImpl();
    }

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }

    @Bean
    @Autowired
    public HotelService hotelService(HotelDao hotelDao, CountryDao countryDao) {
        return new HotelServiceImpl(hotelDao, countryDao);
    }

    @Bean
    @Autowired
    public CountryService countryService(CountryDao countryDao) {
        return new CountryServiceImpl(countryDao);
    }

    @Bean
    @Autowired
    public RoomService roomService(RoomDao roomDao, HotelDao hotelDao) {
        return new RoomServiceImpl(roomDao, hotelDao);
    }

    @Bean
    @Autowired
    public RentService rentService(RentDao rentDao, RoomDao roomDao, UserDao userDao) {
        return new RentServiceImpl(rentDao, roomDao, userDao);
    }

    @Bean
    @Autowired
    public UserService userService(UserDao userDao) {
        return new UserServiceImpl(userDao);
    }

}

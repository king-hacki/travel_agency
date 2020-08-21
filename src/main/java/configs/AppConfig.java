package configs;


import dao.CountryDao;
import dao.HotelDao;
import dao.RoomDao;
import dao.impl.CountryDaoImpl;
import dao.impl.HotelDaoImpl;
import dao.impl.RoomDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import services.CountryService;
import services.HotelService;
import services.RoomService;
import services.impl.CountryServiceImpl;
import services.impl.HotelServiceImpl;
import services.impl.RoomServiceImpl;

@Configuration
@ComponentScan(basePackages = {"dao", "services", "controllers"})
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

}

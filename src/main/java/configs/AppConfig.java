package configs;


import dao.CountryDao;
import dao.HotelDao;
import dao.impl.CountryDaoImpl;
import dao.impl.HotelDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import services.CountryService;
import services.HotelService;
import services.impl.CountryServiceImpl;
import services.impl.HotelServiceImpl;

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
    @Autowired
    public HotelService hotelService(HotelDao hotelDao, CountryDao countryDao) {
        return new HotelServiceImpl(hotelDao, countryDao);
    }

    @Bean
    @Autowired
    public CountryService countryService(CountryDao countryDao) {
        return new CountryServiceImpl(countryDao);
    }

}

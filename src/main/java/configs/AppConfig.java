package configs;


import dao.CountryDao;
import dao.HotelDao;
import dao.impl.CountryDaoImpl;
import dao.impl.HotelDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "dao")
public class AppConfig {

    @Bean
    public HotelDao hotelDao() {
        return new HotelDaoImpl(countryDao());
    }

    @Bean
    public CountryDao countryDao() {
        return new CountryDaoImpl();
    }
}

package dao;

import configs.AppConfig;
import configs.HibernateConfig;
import dao.HotelDao;
import models.Country;
import models.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import security.service.UserService;
import services.RoomService;

import javax.transaction.Transactional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, HibernateConfig.class})
@WebAppConfiguration
@Transactional
public class HotelDaoImplTest {

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void createHotelTest() {
        Session session = sessionFactory.getCurrentSession();
        Country country = new Country(1L, "Country", null);
        session.save(country);
        Country entityCountry = session.find(Country.class, country.getId());
        Hotel expected = new Hotel();
        expected.setName("Hotel");
        expected.setCountry(entityCountry);
        Long expectedId = hotelDao.save(expected);
        Hotel actual = session.find(Hotel.class, expectedId);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllByCountryTest() {
        Session session = sessionFactory.getCurrentSession();
        Hotel hotel_1 = new Hotel(1L, "Hotel_1", null, null);
        Hotel hotel_2 = new Hotel(2L, "Hotel_2", null, null);
        Country country = new Country(1L, "Country", Set.of(hotel_1, hotel_2));
        session.save(country);
        Set<Hotel> expected = Set.of(hotel_1, hotel_2);
        Set<Hotel> actual = hotelDao.findAllByCountry(country);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}
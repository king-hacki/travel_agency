package dao;

import configs.AppConfig;
import configs.HibernateConfig;
import dao.RoomDao;
import models.Country;
import models.Hotel;
import models.Room;
import models.enums.RoomLevel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, HibernateConfig.class})
@WebAppConfiguration
@Transactional
public class RoomDaoImplTest {

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void findAllByHotelTest() {
        Session session = sessionFactory.getCurrentSession();
        Country country = new Country(1L, "Country", null);
        Room room_1 = new Room(1L, 100L, RoomLevel.STANDARD, null, null);
        Room room_2 = new Room(2L, 100L, RoomLevel.STANDARD, null, null);
        Hotel hotel = new Hotel(1L, "Hotel", country, Set.of(room_1, room_2));
        session.save(hotel);
        Hotel hotelEntity = session.find(Hotel.class, hotel.getId());
        Set<Room> expected = Set.of(room_1, room_2);
        Set<Room> actual = roomDao.findAllByHotel(hotelEntity);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}
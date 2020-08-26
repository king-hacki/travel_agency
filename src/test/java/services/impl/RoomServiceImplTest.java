package services.impl;

import configs.AppConfig;
import configs.HibernateConfig;
import dao.HotelDao;
import exceptions.BadDateException;
import models.Country;
import models.Hotel;
import models.Rent;
import models.Room;
import models.enums.RoomLevel;
import models.security_models.Role;
import models.security_models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import javax.transaction.Transactional;
import java.util.LinkedHashSet;
import java.util.List;

import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {AppConfig.class, HibernateConfig.class})
@Transactional
public class RoomServiceImplTest {

    @Mock
    private HotelDao hotelDao;

    @InjectMocks
    private RoomServiceImpl roomService;

    @Test
    public void findAvailableRoomsByPeriodTest() {
        User user = new User(1L, "firstName", "lastName", "email@gmail.com", "test1", new LinkedHashSet<>(), new Role());
        Country country = new Country(1L, "Country", new LinkedHashSet<>());
        Hotel hotel = new Hotel(1L, "Hotel", country, new LinkedHashSet<>());
        country.getHotels().add(hotel);
        Room room1 = new Room(1L, 100L, RoomLevel.STANDARD, hotel, new LinkedHashSet<>());
        Room room2 = new Room(2L, 300L, RoomLevel.STANDARD, hotel, new LinkedHashSet<>());
        hotel.getRooms().add(room1);
        hotel.getRooms().add(room2);
        Rent room1_rent = new Rent(1L, room1, user, now().plusDays(1), now().plusDays(4));
        Rent room2_rent = new Rent(2L, room2, user, now().plusDays(3), now().plusDays(6));
        room1.getRents().add(room1_rent);
        room2.getRents().add(room2_rent);

        Mockito.when(hotelDao.findOne(1L)).thenReturn(hotel);

        List<Room> expected1 = List.of(room2);
        List<Room> actual1 = roomService.findAvailableRoomsByPeriod(1L, now(), now().plusDays(1));
        assertEquals(expected1, actual1);

        List<Room> expected2 = List.of(room1);
        List<Room> actual2 = roomService.findAvailableRoomsByPeriod(1L, now().plusDays(5), now().plusDays(8));
        assertEquals(expected2, actual2);

        List<Room> expected3 = List.of();
        List<Room> actual3 = roomService.findAvailableRoomsByPeriod(1L, now(), now().plusDays(8));
        assertEquals(expected3, actual3);

        List<Room> expected4 = List.of(room1, room2);
        List<Room> actual4 = roomService.findAvailableRoomsByPeriod(1L, now().plusDays(7), now().plusDays(9));
        assertEquals(expected4, actual4);

        assertThrows(BadDateException.class,
                () -> roomService.findAvailableRoomsByPeriod(1L, null, now()));

        assertThrows(BadDateException.class,
                () -> roomService.findAvailableRoomsByPeriod(1L, now(), now().minusDays(2)));

        assertThrows(BadDateException.class,
                () -> roomService.findAvailableRoomsByPeriod(1L, now().minusDays(1), now().plusDays(2)));
    }


}
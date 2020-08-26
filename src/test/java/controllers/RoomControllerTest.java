package controllers;

import configs.AppConfig;
import configs.HibernateConfig;
import lombok.SneakyThrows;
import models.Country;
import models.Hotel;
import models.Room;
import models.enums.RoomLevel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import services.HotelService;
import services.RoomService;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static java.time.LocalDate.now;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {AppConfig.class, HibernateConfig.class})
@WebAppConfiguration
@Transactional
public class RoomControllerTest {

    @Mock
    private RoomService roomService;

    @Mock
    private HotelService hotelService;

    private MockMvc mockMvc;

    Hotel hotel;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new RoomController(roomService, hotelService)).build();
        hotel = new Hotel(1L, "Hotel", new Country(), new HashSet<>());
        Mockito.when(hotelService.getById(1L)).thenReturn(hotel);
    }

    @Test
    public void findRooms() throws Exception {
        mockMvc.perform(get("/room/all/{hotelId}", hotel.getId()))
                .andExpect(model().size(3))
                .andExpect(model().attributeExists("hotelName", "hotelId", "rooms"))
                .andExpect(view().name("room_by_list"))
                .andExpect(status().isOk());
    }

    @Test
    public void findRoomsTest() throws Exception {
        Room room = new Room(1L, 100L, RoomLevel.STANDARD, hotel, new HashSet<>());
        Mockito.when(roomService.findAvailableRoomsByPeriod(hotel.getId(), now(), now())).thenReturn(List.of(room));
        mockMvc.perform(post("/room/{hotelId}", hotel.getId())
                .param("start", now().format(formatter))
                .param("end", now().format(formatter)))
                .andExpect(model().size(4))
                .andExpect(model().attributeExists("availableRooms", "start", "hotel", "end"))
                .andExpect(view().name("room_list"))
                .andExpect(status().isOk());
    }
}
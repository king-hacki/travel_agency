package controllers;

import configs.AppConfig;
import configs.HibernateConfig;
import models.Country;
import models.Hotel;
import models.Rent;
import models.security_models.Role;
import models.security_models.User;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import security.service.UserService;
import services.RentService;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {AppConfig.class, HibernateConfig.class})
@WebAppConfiguration
public class RentControllerTest {

    @Mock
    private RentService rentService;

    @Mock
    private UserService userService;

    MockMvc mockMvc;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    Rent rent = new Rent(1L, null, null, now(), now());
    User user = new User(1L, "test", "test", "test@gmail.com",
            "test", Set.of(rent), new Role(1L, "ROLE_USER"));

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new RentController(rentService, userService)).build();
    }

    @Test
    public void userRentsTest() throws Exception {
        Mockito.when(userService.getUserById(1)).thenReturn(user);
        mockMvc.perform(get("/rent/{userId}", 1))
                        .andExpect(model().size(2))
                        .andExpect(model().attributeExists("userRents", "user"))
                        .andExpect(view().name("user_rents"))
                        .andExpect(status().isOk());

    }

    @Test
    public void bookRoomTest() throws Exception {
        Mockito.when(rentService.bookRoom(1L, now(), now())).thenReturn(rent);
        mockMvc.perform(post("/rent/book")
                .param("room_id", "1")
                .param("start", now().format(formatter))
                .param("end", now().format(formatter)))
                .andExpect(view().name("rent_registrate"))
                .andExpect(status().isOk());

    }

}
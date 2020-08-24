package controllers;

import configs.AppConfig;
import configs.HibernateConfig;
import models.Country;
import models.Hotel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import security.service.UserService;
import services.CountryService;
import services.HotelService;
import services.RoomService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {AppConfig.class, HibernateConfig.class})
@WebAppConfiguration
@Transactional
class ManagerControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ManagerController(userService, countryService, hotelService, roomService)).build();
    }

    @Test
    public void createCountryAction() throws Exception {
        Country country = new Country(1L, "Country", Set.of());
        mockMvc.perform(post("/user_manager/create_country")
                        .sessionAttr("country", country));
        //  END HERE
    }

}
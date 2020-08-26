package controllers;

import configs.AppConfig;
import configs.HibernateConfig;
import models.Country;
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
import services.CountryService;
import services.HotelService;
import services.RoomService;

import javax.transaction.Transactional;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {AppConfig.class, HibernateConfig.class})
@WebAppConfiguration
@Transactional
public class ManagerControllerTest {

    @Autowired
    private UserService userService;

    @Mock
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
        mockMvc.perform(post("/user_manager/create_country")
                        .param("name", "Country Name"))
                        .andExpect(status().is3xxRedirection());
    }

}
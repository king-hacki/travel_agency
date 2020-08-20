package services;

import models.Country;
import models.Hotel;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface HotelService {

    Set<Hotel> getHotelsFromCountry(Country country);

    Hotel createNewHotel(Hotel hotel);

}



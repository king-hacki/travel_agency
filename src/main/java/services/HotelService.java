package services;

import models.Country;
import models.Hotel;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface HotelService {

    Set<Hotel> getHotelsFromCountry(long countryId);

    Hotel createNewHotel(Hotel hotel);

    Hotel getById(long hotelId);

}



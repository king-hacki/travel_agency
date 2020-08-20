package services.impl;

import dao.CountryDao;
import dao.HotelDao;
import models.Country;
import models.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.HotelService;

import java.util.Set;

@Service
public class HotelServiceImpl implements HotelService {

    private HotelDao hotelDao;
    private CountryDao countryDao;

    @Autowired
    public HotelServiceImpl(HotelDao hotelDao, CountryDao countryDao) {
        this.hotelDao = hotelDao;
        this.countryDao = countryDao;
    }

    @Override
    public Set<Hotel> getHotelsFromCountry(Country country) {
        //  TODO custom exception
        if (countryDao.findOne(country.getId()) == null) throw new IllegalArgumentException("Country doesn't exist");
        return hotelDao.findAllByCountry(country);
    }

    @Override
    public Hotel createNewHotel(Hotel hotel) {
        if (hotelDao.findOne(hotel.getId()) != null) throw new IllegalArgumentException("Hotel already exist");
        return hotelDao.create(hotel);
    }


}

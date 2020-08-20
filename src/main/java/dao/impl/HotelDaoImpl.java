package dao.impl;

import dao.CountryDao;
import dao.HotelDao;
import dao.common.AbstractCrudDao;
import models.Country;
import models.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class HotelDaoImpl extends AbstractCrudDao<Hotel> implements HotelDao {

    private CountryDao countryDao;

    @Autowired
    public HotelDaoImpl(CountryDao countryDao) {
        super(Hotel.class);
        this.countryDao = countryDao;
    }

    @Override
    public Set<Hotel> findAllByCountry(Country country) {
        Country countryEntity = countryDao.findOne(country.getId());
        return countryEntity.getHotels();
    }

    @Override
    public Hotel create(Hotel entity) {
        Hotel newHotel = new Hotel(entity.getId(), entity.getName(), entity.getCountry(), entity.getRooms());
        getCurrentSession().save(newHotel);
        return findOne(newHotel.getId());
    }
}

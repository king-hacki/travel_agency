package dao.impl;

import dao.CountryDao;
import dao.HotelDao;
import dao.common.AbstractCrudDao;
import lombok.Data;
import lombok.Setter;
import models.Country;
import models.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
@Transactional
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
}

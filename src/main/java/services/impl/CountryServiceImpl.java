package services.impl;

import dao.CountryDao;
import exceptions.CountryNotExistException;
import models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.CountryService;

import javax.transaction.Transactional;
import java.util.LinkedHashSet;
import java.util.List;

import static java.lang.String.format;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private CountryDao countryDao;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country getById(long countryId) {
        Country countryEntity = countryDao.findOne(countryId);
        if (countryEntity == null)
            throw new CountryNotExistException(format("Country with id: %d didn't find", countryId));
        return countryEntity;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryDao.findAll();
    }

    @Override
    public Country createCountry(Country country) {
        country.setHotels(new LinkedHashSet<>());
        Long countryEntityId = countryDao.save(country);
        return countryDao.findOne(countryEntityId);
    }
}

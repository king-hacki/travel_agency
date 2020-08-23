package services.impl;

import dao.CountryDao;
import exceptions.MissingCountryIdException;
import models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.CountryService;

import javax.transaction.Transactional;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
        System.out.println("countryEntity = " + countryEntity);
        if (countryEntity == null) throw new MissingCountryIdException("Country with given id doesn't exist");
        return countryEntity;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryDao.findAll();
    }

    @Override
    public Country createCountry(Country country) {
        Long countryEntityId = countryDao.save(country);
        return countryDao.findOne(countryEntityId);
    }
}

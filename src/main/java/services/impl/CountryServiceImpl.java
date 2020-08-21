package services.impl;

import dao.CountryDao;
import lombok.Setter;
import models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import services.CountryService;

import javax.transaction.Transactional;

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
        //  TODO create custom exception
        if (countryEntity == null) throw new IllegalArgumentException("Country with given id doesn't exist");
        return countryEntity;
    }
}

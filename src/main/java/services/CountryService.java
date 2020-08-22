package services;

import models.Country;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryService {

    Country getById(long countryId);

    List<Country> getAllCountries();

    Country createCountry(Country country);

}

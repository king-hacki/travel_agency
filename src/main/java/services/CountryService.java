package services;

import models.Country;
import org.springframework.stereotype.Service;

@Service
public interface CountryService {

    Country getById(long countryId);

}

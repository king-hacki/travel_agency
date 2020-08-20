package dao;

import dao.common.CrudHibernateRepository;
import models.Country;
import models.Hotel;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface HotelDao extends CrudHibernateRepository<Hotel> {

    Set<Hotel> findAllByCountry(Country country);

}

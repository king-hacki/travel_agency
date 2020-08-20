package dao;

import dao.common.CrudHibernateRepository;
import models.Country;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDao extends CrudHibernateRepository<Country> {
}

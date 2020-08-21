package dao;

import dao.common.CrudHibernateRepository;
import models.Rent;
import org.springframework.stereotype.Repository;

@Repository
public interface RentDao extends CrudHibernateRepository<Rent> {

}

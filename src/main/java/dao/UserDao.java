package dao;

import dao.common.CrudHibernateRepository;
import models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudHibernateRepository<User> {
}

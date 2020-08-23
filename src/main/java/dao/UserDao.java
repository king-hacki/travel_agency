package dao;

import dao.common.CrudHibernateRepository;
import security.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends CrudHibernateRepository<User> {

    Optional<User> getUserByEmail(String email);

}

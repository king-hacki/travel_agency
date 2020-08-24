package dao.impl;

import dao.UserDao;
import dao.common.AbstractCrudDao;
import models.security_models.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class UserDaoImpl extends AbstractCrudDao<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<User> getUserByEmail(String email) {
        return getCurrentSession()
                .createQuery("SELECT user FROM User user WHERE user.email = :email")
                .setParameter("email", email)
                .setMaxResults(1)
                .uniqueResultOptional();
    }
}

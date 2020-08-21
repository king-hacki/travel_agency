package dao.impl;

import dao.UserDao;
import dao.common.AbstractCrudDao;
import models.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDaoImpl extends AbstractCrudDao<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User create(User entity) {
        return null;
    }

}

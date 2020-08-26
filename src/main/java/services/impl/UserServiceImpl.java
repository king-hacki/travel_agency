package services.impl;

import dao.UserDao;
import exceptions.UserNotFoundByIdException;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.UserService;

import javax.transaction.Transactional;
import java.util.List;

import static java.lang.String.format;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> allUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(long userId) {
        User userEntity = userDao.findOne(userId);
        if (userEntity == null)
            throw new UserNotFoundByIdException(format("No user with id : %d", userId));
        return userEntity;
    }
}

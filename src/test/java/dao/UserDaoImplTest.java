package dao;

import configs.AppConfig;
import configs.HibernateConfig;
import dao.HotelDao;
import dao.RoleDao;
import dao.UserDao;
import models.security_models.Role;
import models.security_models.User;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.util.Optional;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, HibernateConfig.class})
@WebAppConfiguration
@Transactional
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void getUserByEmailTest() {
        Role role = roleDao.findOne(1L);
        User user = new User(1L, "firstName", "lastName", "test111111111111111@gmail.com", "test", new TreeSet<>(), role);
        sessionFactory.getCurrentSession().save(user);
        User expected = userDao.findOne(user.getId());
        Optional<User> actual = userDao.getUserByEmail("test111111111111111@gmail.com");
        assertEquals(expected, actual.get());
    }

}
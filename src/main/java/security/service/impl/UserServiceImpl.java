package security.service.impl;

import configs.AppConfig;
import configs.HibernateConfig;
import dao.RoleDao;
import dao.UserDao;
import models.security_models.Role;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import models.security_models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import security.service.UserService;

import javax.transaction.Transactional;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@ComponentScan(basePackageClasses = AppConfig.class)
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.roleDao = roleDao;
        this.userDao = userDao;
    }

    @Override
    public List<User> allUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(long userId) {
        User userEntity = userDao.findOne(userId);
        //  TODO custom exception
        if (userEntity == null) throw new IllegalArgumentException("no user");
        return userEntity;
    }

    @Override
    public void createUser(User user) {
        Role userRole = roleDao.findOne(1L);
        String encodePassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setRents(new LinkedHashSet<>());
        user.setRole(userRole);
        userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userDao.getUserByEmail(email);
        return user.orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}

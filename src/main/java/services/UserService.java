package services;

import models.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface UserService {

    List<User> allUsers();

    User getUserById(long userId);
}

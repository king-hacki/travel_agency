package security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import models.security_models.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface UserService extends UserDetailsService {

    List<User> allUsers();
    User getUserById(long userId);
}

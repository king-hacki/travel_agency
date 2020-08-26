package services.impl;

import dao.RentDao;
import dao.RoomDao;
import dao.UserDao;
import exceptions.UserNotFoundException;
import models.Rent;
import models.Room;
import models.security_models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import services.RentService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

import static java.lang.String.format;

@Service
@Transactional
public class RentServiceImpl implements RentService {

    private RentDao rentDao;
    private RoomDao roomDao;
    private UserDao userDao;

    @Autowired
    public RentServiceImpl(RentDao rentDao, RoomDao roomDao, UserDao userDao) {
        this.rentDao = rentDao;
        this.roomDao = roomDao;
        this.userDao = userDao;
    }

    @Override
    public Rent bookRoom(long roomId, LocalDate start, LocalDate end) {
        Room roomEntity = roomDao.findOne(roomId);
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails)
            email = ((UserDetails) principal).getUsername();
        else
            email = principal.toString();
        User userEntity = userDao.getUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(format("User with email: %s not found", email)));
        Rent newRent = new Rent();
        newRent.setEndRentDate(end);
        newRent.setStartRentDate(start);
        newRent.setUser(userEntity);
        newRent.setRoom(roomEntity);
        Long rentEntityId = rentDao.save(newRent);
        return rentDao.findOne(rentEntityId);
    }

}

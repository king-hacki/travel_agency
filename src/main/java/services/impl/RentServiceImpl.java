package services.impl;

import dao.RentDao;
import dao.RoomDao;
import dao.UserDao;
import models.Rent;
import models.Room;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.RentService;

import javax.transaction.Transactional;
import java.time.LocalDate;

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
        if (roomEntity == null) throw new IllegalArgumentException("Room doesn't exist");
        //  TODO change in security
        User userEntity = userDao.findOne(1L);
        Rent newRent = new Rent();
        newRent.setEndRentDate(end);
        newRent.setStartRentDate(start);
        newRent.setUser(userEntity);
        newRent.setRoom(roomEntity);
        Long rentEntityId = rentDao.save(newRent);
        return rentDao.findOne(rentEntityId);
    }

}

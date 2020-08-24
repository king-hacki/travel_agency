package dao.impl;

import dao.HotelDao;
import dao.RoomDao;
import dao.common.AbstractCrudDao;
import models.Hotel;
import models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
@Transactional
public class RoomDaoImpl extends AbstractCrudDao<Room> implements RoomDao {

    private HotelDao hotelDao;

    @Autowired
    public RoomDaoImpl(HotelDao hotelDao) {
        super(Room.class);
        this.hotelDao = hotelDao;
    }

    @Override
    public Set<Room> findAllByHotel(Hotel hotel) {
        Hotel hotelEntity = hotelDao.findOne(hotel.getId());
        return hotelEntity.getRooms();
    }

}

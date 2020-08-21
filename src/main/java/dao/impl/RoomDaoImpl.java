package dao.impl;

import dao.RoomDao;
import dao.common.AbstractCrudDao;
import models.Hotel;
import models.Room;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class RoomDaoImpl extends AbstractCrudDao<Room> implements RoomDao {

    public RoomDaoImpl() {
        super(Room.class);
    }

    @Override
    public Set<Room> findAllByHotel(Hotel hotel) {
        return null;
    }

    @Override
    public Room create(Room entity) {
        return null;
    }
}

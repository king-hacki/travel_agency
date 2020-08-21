package dao;

import dao.common.CrudHibernateRepository;
import models.Hotel;
import models.Room;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoomDao extends CrudHibernateRepository<Room> {

    Set<Room> findAllByHotel(Hotel hotel);


}

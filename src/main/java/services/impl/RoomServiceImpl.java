package services.impl;

import dao.HotelDao;
import dao.RoomDao;
import models.Country;
import models.Hotel;
import models.Rent;
import models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.RoomService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private RoomDao roomDao;
    private HotelDao hotelDao;

    @Autowired
    public RoomServiceImpl(RoomDao roomDao, HotelDao hotelDao) {
        this.hotelDao = hotelDao;
        this.roomDao = roomDao;
    }

    @Override
    public List<Room> findAvailableRoomsByPeriod(long hotelId, LocalDate start, LocalDate end) {
        if (start.isAfter(end)) throw new IllegalArgumentException("bad date");
        if (start.isBefore(LocalDate.now())) throw new IllegalArgumentException("bad date");
        Hotel hotelEntity = hotelDao.findOne(hotelId);
        Set<Room> rooms = hotelEntity.getRooms();
        rooms.forEach(room -> room.getRents().forEach(System.out::println));
        List<Room> freeRooms = new ArrayList<>();
        room: for (Room room : rooms) {
            //  add rooms if start date is after last rent
            if (room.getRents().isEmpty() || new ArrayList<>(room.getRents()).get(room.getRents().size() - 1).getEndRentDate().isBefore(start)) {
                freeRooms.add(room);
            } else {
                //  else go through all rents
                for (Rent rent : room.getRents()) {
                    System.out.println("rent = " + rent);
                    LocalDate rentStart = rent.getStartRentDate();
                    LocalDate rentEnd = rent.getEndRentDate();
                    if (start.isBefore(rentStart) && end.isBefore(rentStart)) {
                        freeRooms.add(room);
                        break;
                    }
                    if ((start.isBefore(rentStart) || start.isEqual(rentStart)) && end.isAfter(rentStart))
                        break room;

                    if ((start.isAfter(rentStart) || start.isEqual(rentStart)) &&
                            (start.isBefore(rentEnd) || start.isEqual(rentEnd)))
                        break room;
                }
            }
        }
        return freeRooms;
    }

    @Override
    public Room createNewRoom(Room room, long hotelId) {
        Hotel hotelEntity = hotelDao.findOne(hotelId);
        if (hotelEntity == null) throw new IllegalArgumentException("Country doesn't exist");
        room.setHotel(hotelEntity);
        Long roomEntityId = roomDao.save(room);
        Room roomEntity = roomDao.findOne(roomEntityId);
        hotelEntity.getRooms().add(roomEntity);
        return roomEntity;
    }


}

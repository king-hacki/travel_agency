package services.impl;

import dao.HotelDao;
import dao.RoomDao;
import models.Hotel;
import models.Rent;
import models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import services.RoomService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        List<Room> freeRooms = new ArrayList<>();
        room: for (Room room : rooms) {
            //  add rooms if start date is after last rent
            if (new ArrayList<>(room.getRents()).get(room.getRents().size() - 1).getEndRentDate().isBefore(start))
                freeRooms.add(room);
            else
                //  else go through all rents
                for (Rent rent : room.getRents()) {
                    System.out.println("rent = " + rent);
                    LocalDate rentStart = rent.getStartRentDate();
                    LocalDate rentEnd = rent.getEndRentDate();
                    if (start.isBefore(rentStart) && end.isBefore(rentStart)) {
                        freeRooms.add(room);
                        continue;
                    }
                    if ((start.isBefore(rentStart) || start.isEqual(rentStart)) && end.isAfter(rentStart))
                        break room;
                    if ((start.isAfter(rentStart) || start.isEqual(rentStart)) &&
                            (start.isBefore(rentEnd) || start.isEqual(rentEnd)))
                        break room;
                }
        }
        return freeRooms;
    }


}

package services.impl;

import configs.AppConfig;
import configs.HibernateConfig;
import dao.HotelDao;
import dao.RoomDao;
import exceptions.BadDateException;
import models.Hotel;
import models.Rent;
import models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import services.RoomService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (start == null || end == null) throw new BadDateException("date can't be empty");
        if (start.isAfter(end)) {
            throw new BadDateException(
                    format("end date : %s can't be before start date : %s", end.format(formatter), start.format(formatter)));
        }
        if (start.isBefore(LocalDate.now()))
            throw new BadDateException(format("Date : %s already in a past", start.format(formatter)));
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
        room.setHotel(hotelEntity);
        Long roomEntityId = roomDao.save(room);
        Room roomEntity = roomDao.findOne(roomEntityId);
        hotelEntity.getRooms().add(roomEntity);
        return roomEntity;
    }


}

package services;

import models.Hotel;
import models.Room;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface RoomService {

    List<Room> findAvailableRoomsByPeriod(long hotelId, LocalDate start, LocalDate end);

    Room createNewRoom(Room room, long hotelId);

}

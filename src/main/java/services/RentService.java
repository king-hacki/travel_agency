package services;

import models.Rent;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public interface RentService {

    Rent bookRoom(long roomId, LocalDate start, LocalDate end);

}

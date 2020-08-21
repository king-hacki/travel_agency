package models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import models.enums.RentStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
public class Rent implements Comparable<Rent> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDate startRentDate;

    private LocalDate endRentDate;

    @Enumerated(STRING)
    private RentStatus status;

    @Override
    public int compareTo(Rent rent) {
        return startRentDate.compareTo(rent.startRentDate);
    }
}

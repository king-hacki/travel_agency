package models;

import lombok.*;
import models.security_models.User;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public int compareTo(Rent rent) {
        return startRentDate.compareTo(rent.startRentDate);
    }
}

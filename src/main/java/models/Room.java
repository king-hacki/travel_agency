package models;

import lombok.*;
import models.enums.RoomLevel;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.TreeSet;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull(message = "Room number can't be empty")
    @Digits(integer = 6, fraction = 0)
    private Long number;

    @Enumerated(STRING)
    private RoomLevel level;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "room", fetch = LAZY)
    @OrderBy("startRentDate ASC")
    private Set<Rent> rents = new TreeSet<>();

}

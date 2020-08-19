package models;

import lombok.Data;
import lombok.NoArgsConstructor;
import models.enums.RoomLevel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull(message = "Room must have number")
    private Long number;

    @Enumerated(STRING)
    private RoomLevel level;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "room", fetch = LAZY)
    private Set<Rent> rents = new LinkedHashSet<>();

}

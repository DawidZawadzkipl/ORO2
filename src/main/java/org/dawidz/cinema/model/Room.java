package org.dawidz.cinema.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;
    private int roomNumber;
    @OneToMany(mappedBy = "room")
    @Setter(AccessLevel.NONE)
    private List<Seat> seats = new ArrayList<>();

    private boolean active = true;
}

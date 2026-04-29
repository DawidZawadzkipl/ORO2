package org.dawidz.cinema.model;


import jakarta.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int row;
    private int seatNumber;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}

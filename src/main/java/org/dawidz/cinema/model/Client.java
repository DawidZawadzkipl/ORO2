package org.dawidz.cinema.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String login;
//    private String email;
//    private String phone;
    @OneToMany(mappedBy = "client")
    private List<Ticket> tickets = new ArrayList<>();
}

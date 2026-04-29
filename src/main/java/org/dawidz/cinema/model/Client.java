package org.dawidz.cinema.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;
    private String login;
//    private String email;
//    private String phone;
    @OneToMany(mappedBy = "client")
    @Setter(AccessLevel.NONE)
    private List<Ticket> tickets = new ArrayList<>();

    private boolean active = true;
}

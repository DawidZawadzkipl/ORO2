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
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToMany(mappedBy = "movie")
    @Setter(AccessLevel.NONE)
    private List<Seans> seansList = new ArrayList<>();
    private String title;

    private boolean active = true;
}

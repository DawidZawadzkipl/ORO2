package org.dawidz.cinema.Repository;

import org.dawidz.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}

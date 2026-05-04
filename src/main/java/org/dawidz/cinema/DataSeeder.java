package org.dawidz.cinema.config;

import lombok.RequiredArgsConstructor;
import org.dawidz.cinema.model.*;
import org.dawidz.cinema.model.enums.TicketStatus;
import org.dawidz.cinema.model.enums.TicketType;
import org.dawidz.cinema.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(
            MovieRepository movieRepository,
            RoomRepository roomRepository,
            SeatRepository seatRepository,
            SeansRepository seansRepository,
            ClientRepository clientRepository,
            TicketRepository ticketRepository
    ) {
        return args -> {
            if (movieRepository.count() > 0) {
                return;
            }
            Movie movie1 = new Movie();
            movie1.setTitle("Interstellar");

            Movie movie2 = new Movie();
            movie2.setTitle("Inception");

            movieRepository.save(movie1);
            movieRepository.save(movie2);

            Room room1 = new Room();
            room1.setRoomNumber(1);

            Room room2 = new Room();
            room2.setRoomNumber(2);

            roomRepository.save(room1);
            roomRepository.save(room2);

            Seat seat1 = new Seat();
            seat1.setRow(1);
            seat1.setSeatNumber(1);
            seat1.setRoom(room1);

            Seat seat2 = new Seat();
            seat2.setRow(1);
            seat2.setSeatNumber(2);
            seat2.setRoom(room1);

            Seat seat3 = new Seat();
            seat3.setRow(1);
            seat3.setSeatNumber(1);
            seat3.setRoom(room2);

            seatRepository.save(seat1);
            seatRepository.save(seat2);
            seatRepository.save(seat3);

            Seans seans1 = new Seans();
            seans1.setMovie(movie1);
            seans1.setRoom(room1);
            seans1.setDateTime(LocalDateTime.of(2026, 5, 10, 18, 0));

            Seans seans2 = new Seans();
            seans2.setMovie(movie1);
            seans2.setRoom(room2);
            seans2.setDateTime(LocalDateTime.of(2026, 5, 11, 20, 0));

            Seans seans3 = new Seans();
            seans3.setMovie(movie2);
            seans3.setRoom(room1);
            seans3.setDateTime(LocalDateTime.of(2026, 5, 12, 19, 0));

            seansRepository.save(seans1);
            seansRepository.save(seans2);
            seansRepository.save(seans3);

            Client client1 = new Client();
            client1.setName("Jan Kowalski");
            client1.setLogin("jkowalski");

            Client client2 = new Client();
            client2.setName("Anna Nowak");
            client2.setLogin("anowak");

            clientRepository.save(client1);
            clientRepository.save(client2);

            Ticket ticket1 = new Ticket();
            ticket1.setClient(client1);
            ticket1.setSeans(seans1);
            ticket1.setSeat(seat1);
            ticket1.setType(TicketType.NORMAL);
            ticket1.setStatus(TicketStatus.CONFIRMED);
            ticket1.setDateBought(LocalDate.of(2026, 5, 1));

            Ticket ticket2 = new Ticket();
            ticket2.setClient(client2);
            ticket2.setSeans(seans1);
            ticket2.setSeat(seat2);
            ticket2.setType(TicketType.STUDENT);
            ticket2.setStatus(TicketStatus.RESERVED);
            ticket2.setDateBought(LocalDate.of(2026, 5, 2));

            Ticket ticket3 = new Ticket();
            ticket3.setClient(client1);
            ticket3.setSeans(seans2);
            ticket3.setSeat(seat3);
            ticket3.setType(TicketType.NORMAL);
            ticket3.setStatus(TicketStatus.CONFIRMED);
            ticket3.setDateBought(LocalDate.of(2026, 5, 3));

            ticketRepository.save(ticket1);
            ticketRepository.save(ticket2);
            ticketRepository.save(ticket3);
        };
    }
}
package org.dawidz.cinema;
import org.dawidz.cinema.model.*;
import org.dawidz.cinema.model.enums.TicketStatus;
import org.dawidz.cinema.model.enums.TicketType;
import org.dawidz.cinema.repository.*;
import org.dawidz.cinema.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
public class CinemaQueriesTests {
    @Autowired
    MovieRepository movieRepository;
    @Autowired RoomRepository roomRepository;
    @Autowired SeatRepository seatRepository;
    @Autowired SeansRepository seansRepository;
    @Autowired ClientRepository clientRepository;
    @Autowired TicketRepository ticketRepository;

    private final Pageable pageable = PageRequest.of(0, 10);

    @Test
    void shouldFindMoviesPlayedInRoom() {
        TestData data = createTestData();

        var result = movieRepository.findMoviesByRoomId(data.room1.getId(), pageable);

        assertThat(result.getContent())
                .extracting(MovieDto::title)
                .containsExactlyInAnyOrder("Interstellar", "Inception");
    }

    @Test
    void shouldFindSeansesPlayedInRoom() {
        TestData data = createTestData();

        var result = seansRepository.findSeansesByRoomId(data.room1.getId(), pageable);

        assertThat(result.getContent()).hasSize(2);
    }

    @Test
    void shouldFindSeansesByMovieId() {
        TestData data = createTestData();

        var result = seansRepository.findSeansOfMovieById(data.movie1.getId(), pageable);

        assertThat(result.getContent()).hasSize(2);
    }

    @Test
    void shouldFindSeansesByMovieTitle() {
        createTestData();

        var result = seansRepository.findSeansByMovieTitle("Interstellar", pageable);

        assertThat(result.getContent()).hasSize(2);
    }

    @Test
    void shouldFindClientsBySeansId() {
        TestData data = createTestData();

        var result = seansRepository.findClientsBySeansId(data.seans1.getId(), pageable);

        assertThat(result.getContent())
                .extracting(ClientDto::login)
                .contains("jkowalski");
    }

    @Test
    void shouldFindSeansesByClientId() {
        TestData data = createTestData();

        var result = ticketRepository.findSeansesByClientId(data.client1.getId(), pageable);

        assertThat(result.getContent()).hasSize(2);
    }

    @Test
    void shouldFindSeansesByClientLogin() {
        createTestData();

        var result = ticketRepository.findSeansesByClientLogin("jkowalski", pageable);

        assertThat(result.getContent()).hasSize(2);
    }

    @Test
    void shouldCountOccupiedSeatsBySeansId() {
        TestData data = createTestData();

        long result = seansRepository.totalOccupiedSeatsBySeansId(data.seans1.getId());

        assertThat(result).isEqualTo(2);
    }

    @Test
    void shouldCountRoomsByMovieId() {
        TestData data = createTestData();

        long result = seansRepository.countRoomsByMovieId(data.movie1.getId());

        assertThat(result).isEqualTo(2);
    }

    @Test
    void shouldCountBoughtTicketsByClientBetweenDates() {
        TestData data = createTestData();

        long result = ticketRepository.countTicketsByClientIdBetweenDates(
                data.client1.getId(),
                LocalDate.of(2026, 5, 1),
                LocalDate.of(2026, 5, 31)
        );

        assertThat(result).isEqualTo(2);
    }

    @Test
    void shouldNotReturnInactiveMovie() {
        TestData data = createTestData();

        data.movie1.setActive(false);
        movieRepository.save(data.movie1);

        var result = movieRepository.findMoviesByRoomId(data.room1.getId(), pageable);

        assertThat(result.getContent())
                .extracting(MovieDto::title)
                .doesNotContain("Interstellar");
    }

    @Test
    void shouldNotReturnCancelledSeans() {
        TestData data = createTestData();

        data.seans1.setCancelled(true);
        seansRepository.save(data.seans1);

        var result = seansRepository.findSeansesByRoomId(data.room1.getId(), pageable);

        assertThat(result.getContent())
                .extracting(SeansDto::id)
                .doesNotContain(data.seans1.getId());
    }

    @Test
    void shouldNotCountExpiredTicketAsOccupiedSeat() {
        TestData data = createTestData();

        data.ticket2.setStatus(TicketStatus.EXPIRED);
        ticketRepository.save(data.ticket2);

        long result = seansRepository.totalOccupiedSeatsBySeansId(data.seans1.getId());

        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldNotCountReservedTicketAsBought() {
        TestData data = createTestData();

        long result = ticketRepository.countTicketsByClientIdBetweenDates(
                data.client2.getId(),
                LocalDate.of(2026, 5, 1),
                LocalDate.of(2026, 5, 31)
        );

        assertThat(result).isEqualTo(0);
    }

    private TestData createTestData() {
        Movie movie1 = new Movie();
        movie1.setTitle("Interstellar");
        movieRepository.save(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("Inception");
        movieRepository.save(movie2);

        Room room1 = new Room();
        room1.setRoomNumber(1);
        roomRepository.save(room1);

        Room room2 = new Room();
        room2.setRoomNumber(2);
        roomRepository.save(room2);

        Seat seat1 = new Seat();
        seat1.setRow(1);
        seat1.setSeatNumber(1);
        seat1.setRoom(room1);
        seatRepository.save(seat1);

        Seat seat2 = new Seat();
        seat2.setRow(1);
        seat2.setSeatNumber(2);
        seat2.setRoom(room1);
        seatRepository.save(seat2);

        Seat seat3 = new Seat();
        seat3.setRow(1);
        seat3.setSeatNumber(1);
        seat3.setRoom(room2);
        seatRepository.save(seat3);

        Seans seans1 = new Seans();
        seans1.setMovie(movie1);
        seans1.setRoom(room1);
        seans1.setDateTime(LocalDateTime.of(2026, 5, 10, 18, 0));
        seansRepository.save(seans1);

        Seans seans2 = new Seans();
        seans2.setMovie(movie1);
        seans2.setRoom(room2);
        seans2.setDateTime(LocalDateTime.of(2026, 5, 11, 20, 0));
        seansRepository.save(seans2);

        Seans seans3 = new Seans();
        seans3.setMovie(movie2);
        seans3.setRoom(room1);
        seans3.setDateTime(LocalDateTime.of(2026, 5, 12, 19, 0));
        seansRepository.save(seans3);

        Client client1 = new Client();
        client1.setName("Jan Kowalski");
        client1.setLogin("jkowalski");
        clientRepository.save(client1);

        Client client2 = new Client();
        client2.setName("Anna Nowak");
        client2.setLogin("anowak");
        clientRepository.save(client2);

        Ticket ticket1 = new Ticket();
        ticket1.setClient(client1);
        ticket1.setSeans(seans1);
        ticket1.setSeat(seat1);
        ticket1.setStatus(TicketStatus.CONFIRMED);
        ticket1.setType(TicketType.NORMAL);
        ticket1.setDateBought(LocalDate.of(2026, 5, 1));
        ticketRepository.save(ticket1);

        Ticket ticket2 = new Ticket();
        ticket2.setClient(client2);
        ticket2.setSeans(seans1);
        ticket2.setSeat(seat2);
        ticket2.setStatus(TicketStatus.RESERVED);
        ticket2.setType(TicketType.STUDENT);
        ticket2.setDateBought(LocalDate.of(2026, 5, 2));
        ticketRepository.save(ticket2);

        Ticket ticket3 = new Ticket();
        ticket3.setClient(client1);
        ticket3.setSeans(seans2);
        ticket3.setSeat(seat3);
        ticket3.setStatus(TicketStatus.CONFIRMED);
        ticket3.setType(TicketType.NORMAL);
        ticket3.setDateBought(LocalDate.of(2026, 5, 3));
        ticketRepository.save(ticket3);

        return new TestData(
                movie1, movie2,
                room1, room2,
                seans1, seans2, seans3,
                client1, client2,
                ticket1, ticket2, ticket3
        );
    }

    private record TestData(
            Movie movie1,
            Movie movie2,
            Room room1,
            Room room2,
            Seans seans1,
            Seans seans2,
            Seans seans3,
            Client client1,
            Client client2,
            Ticket ticket1,
            Ticket ticket2,
            Ticket ticket3
    ) {
    }
}

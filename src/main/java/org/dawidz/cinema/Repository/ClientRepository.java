package org.dawidz.cinema.Repository;

import org.dawidz.cinema.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}

package org.dawidz.cinema.mapper;

import org.dawidz.cinema.dto.ClientDto;
import org.dawidz.cinema.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDto toDto(Client client){
        return new ClientDto(client.getId(), client.getName(), client.getLogin());
    }
}

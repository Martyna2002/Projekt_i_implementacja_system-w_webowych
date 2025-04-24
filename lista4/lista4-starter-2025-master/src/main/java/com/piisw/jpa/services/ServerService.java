package com.piisw.jpa.services;

import com.piisw.jpa.entities.Event;
import com.piisw.jpa.entities.Server;
import com.piisw.jpa.repositories.EventRepository;
import com.piisw.jpa.repositories.ServerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ServerService {

    private final ServerRepository serverRepository;
    private final EventRepository eventRepository;

    public Optional<Server> findByName(String name) {
        return serverRepository.findByNameAndIsActiveTrue(name);
    }
    public List<Event> getEventsForFollower(String userId) {
        return eventRepository.findByFollowers_UserId(userId);
    }



}


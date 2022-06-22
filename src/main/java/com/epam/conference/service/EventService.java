package com.epam.conference.service;

import com.epam.conference.entity.event.Event;
import com.epam.conference.entity.event.EventDto;
import com.epam.conference.repository.EventRepository;
import com.epam.conference.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
@Service
public class EventService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    EventRepository eventRepository;

    DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-mm-dd");

    public List<Event> getListOfEvents(){
        return eventRepository.findAll();
    }

    public Event addNewEvent(EventDto eventDto) throws ParseException {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setDate(dateTimeFormat.parse(eventDto.getDate()));
        event.setDescription(eventDto.getDescription());
        return eventRepository.save(event);
    }
}

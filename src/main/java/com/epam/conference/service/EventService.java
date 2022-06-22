package com.epam.conference.service;

import com.epam.conference.entity.dto.ReportDto;
import com.epam.conference.entity.event.Event;
import com.epam.conference.entity.dto.EventDto;
import com.epam.conference.entity.event.Report;
import com.epam.conference.repository.EventRepository;
import com.epam.conference.repository.ReportRepository;
import com.epam.conference.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    UserRepository userRepository;

    DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-mm-dd");

    public List<Event> getListOfEvents(){
        return eventRepository.findAll();
    }

    public Event addNewEvent(EventDto eventDto){
        Event event = new Event();
        event.setName(eventDto.getName());
        try {
            event.setDate(dateTimeFormat.parse(eventDto.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        event.setDescription(eventDto.getDescription());
        return eventRepository.save(event);
    }
    public Event getEventById(Long id){
        return eventRepository.getById(id);
    }

    public Report addNewReport(Long id, ReportDto reportDto) {
        Report report = new Report();
        report.setName(reportDto.getName());
        report.setDescription(reportDto.getDescription());
        if(reportDto.getSpeakerId()!=null){
            report.setSpeaker(userRepository.getById(reportDto.getSpeakerId()));
        }
        reportRepository.save(report);
        eventRepository.getById(id).getReports().add(report);
        eventRepository.save(eventRepository.getById(id));
        return report;
    }
}

package com.epam.conference.service;

import com.epam.conference.entity.dto.ReportDto;
import com.epam.conference.entity.event.Event;
import com.epam.conference.entity.dto.EventDto;
import com.epam.conference.entity.event.Report;
import com.epam.conference.repository.EventRepository;
import com.epam.conference.repository.ReportRepository;
import com.epam.conference.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private UserRepository userRepository;

    DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Page<Event> getListOfEventsAfterCurrent(Pageable pageable){
        return  new PageImpl<>(eventRepository.findAllByDateAfter(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())));
    }
    public Page<Event> getListOfEventsAfterCurrentSortedByUsersSize(Pageable pageable){
        List<Event> events = eventRepository.findAllByDateAfter(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .stream().sorted(Comparator.comparing(Event::getUsersSize)).collect(Collectors.toList());
        return ServiceUtils.toPage(events,pageable);
    }

    public Page<Event> getListOfEventsAfterCurrentSortedByReportsSize(Pageable pageable){
        List<Event> events = eventRepository.findAllByDateAfter(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .stream().sorted(Comparator.comparing(Event::getReportsSize)).collect(Collectors.toList());
        return ServiceUtils.toPage(events,pageable);
    }

    public Page<Event> getListOfEventsBeforeCurrent(Pageable pageable){
        return new PageImpl<>(eventRepository.findAll(pageable).filter(event -> {
            try {
                return event.getDate().before(dateTimeFormat.parse(String.valueOf(LocalDate.now())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        }).toList());
    }

    public Event addNewEvent(EventDto eventDto){
        Event event = new Event();
        event.setName(eventDto.getName());
        try {
            System.out.println(eventDto.getDate());
            event.setDate(dateTimeFormat.parse(eventDto.getDate()));
            System.out.println(event.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        event.setDescription(eventDto.getDescription());
        return eventRepository.save(event);
    }
    public Event getEventById(Long id){
        return eventRepository.getById(id);
    }

    public Event updateEvent(EventDto eventDto,Long id) {
        Event event = eventRepository.getById(id);
        event.setName(eventDto.getName());
        try {
            event.setDate(dateTimeFormat.parse(eventDto.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        event.setDescription(eventDto.getDescription());
        return eventRepository.save(event);
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

    public Report updateReport(ReportDto reportDto, Long reportId) {
        Report report = reportRepository.getById(reportId);
        report.setName(reportDto.getName());
        report.setDescription(reportDto.getDescription());
        if(reportDto.getSpeakerId() != null){
            report.setSpeaker(userRepository.getById(reportDto.getSpeakerId()));
        }
        else {
            report.setSpeaker(null);
        }
        return reportRepository.save(report);
    }
}

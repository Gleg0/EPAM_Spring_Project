package com.epam.conference.service;

import com.epam.conference.entity.event.Report;
import com.epam.conference.repository.EventRepository;
import com.epam.conference.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private EventRepository eventRepository;
    public Report getReportById(Long id) {
        return reportRepository.getById(id);
    }
    public List<Report> getAccepted(Long id){
        List<Report> list = eventRepository.getById(id).getReports();
        List<Report> result = list.stream().filter(Report::getAccepted).collect(Collectors.toList());
        System.out.println(result);
        return result;
    }
}

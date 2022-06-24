package com.epam.conference.service;

import com.epam.conference.entity.event.Report;
import com.epam.conference.repository.EventRepository;
import com.epam.conference.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;
    public Report getReportById(Long id) {
        return reportRepository.getById(id);
    }
}

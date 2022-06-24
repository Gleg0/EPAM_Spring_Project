package com.epam.conference.service;

import com.epam.conference.entity.event.Report;
import com.epam.conference.repository.ReportRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
class ReportServiceTest {
    @Autowired
    private ReportService reportService;
    @MockBean
    private ReportRepository reportRepository;
    @Test
    void getReportById() {
        Report expected = new Report();
        Long id = 1L;
        Report actual = reportService.getReportById(id);
        Mockito.verify(reportRepository).getById(id);
    }
}
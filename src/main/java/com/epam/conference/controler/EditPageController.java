package com.epam.conference.controler;

import com.epam.conference.entity.dto.EventDto;
import com.epam.conference.entity.dto.ReportDto;
import com.epam.conference.entity.event.Event;
import com.epam.conference.entity.event.Report;
import com.epam.conference.service.EventService;
import com.epam.conference.service.ReportService;
import com.epam.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/edit")
@Controller
public class EditPageController {

    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReportService reportService;

    @ModelAttribute("report")
    public ReportDto reportDto() {
        return new ReportDto();
    }

    @ModelAttribute("event")
    public EventDto eventDto() {
        return new EventDto();
    }

    @GetMapping("/event")
    public String mainGetAddEvent(Model model,@RequestParam(value = "eventId") Long eventId){
        model.addAttribute("type","event");
        Event event = eventService.getEventById(eventId);
        EventDto eventDto = new EventDto();
        eventDto.setName(event.getName());
        eventDto.setDescription(event.getDescription());
        eventDto.setDate(event.getDate().toString());
        model.addAttribute("event",eventDto);
        return "editPage";
    }
    @GetMapping("/report")
    public String mainGetAddReport(Model model,@RequestParam(value = "reportId") Long reportId){
        model.addAttribute("type","report");
        Report report = reportService.getReportById(reportId);
        ReportDto reportDto = new ReportDto();
        reportDto.setName(report.getName());
        reportDto.setDescription(report.getDescription());
        if(report.getSpeaker()  != null){
            reportDto.setSpeakerId(report.getSpeaker().getId());
        }
        model.addAttribute("report",reportDto);
        model.addAttribute("speakers",userService.allSpeakers());
        return "editPage";
    }
    @PostMapping("/event")
    public String mainPostAddEvent(@ModelAttribute("event") @Valid EventDto eventDto,@RequestParam(value = "eventId") Long eventId){
        eventService.updateEvent(eventDto,eventId);
        return "redirect:/events";
    }
    @PostMapping("/report")
    public String mainPostAddReport(Model model,@ModelAttribute("report") @Valid ReportDto reportDto,@RequestParam(value = "reportId") Long reportId){
        eventService.updateReport(reportDto,reportId);
        return "redirect:/events";
    }
}

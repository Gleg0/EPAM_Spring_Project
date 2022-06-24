package com.epam.conference.controler;

import com.epam.conference.service.EventService;
import com.epam.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RequestMapping("/events")
@Controller
public class EventsPageController {
    @Autowired
    EventService eventService;

    @GetMapping("")
    public String events(Model model,@PageableDefault(size = 10) Pageable pageable){
        model.addAttribute("eventList",eventService.getListOfEventsAfterCurrent(pageable));
        return "eventsPage";
    }

    @GetMapping("/date")
    public String eventsDate(Model model,@PageableDefault(sort = {"date"},direction = Sort.Direction.ASC) Pageable pageable){
        model.addAttribute("eventList",eventService.getListOfEventsAfterCurrent(pageable));
        return "eventsPage";
    }

    @GetMapping("/dateDesc")
    public String eventsDateDesc(Model model,@PageableDefault(sort = {"date"},direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("eventList",eventService.getListOfEventsAfterCurrent(pageable));
        return "eventsPage";
    }

    @GetMapping("/reportsSize")
    public String eventsReportSize(Model model,@PageableDefault(value = 10) Pageable pageable){
        model.addAttribute("eventList",eventService.getListOfEventsAfterCurrentSortedByReportsSize(pageable));
        return "eventsPage";
    }
    @GetMapping("/reportsSizeDesc")
    public String eventsReportsSizeDesc(Model model,@PageableDefault(value = 10,direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("eventList",eventService.getListOfEventsAfterCurrentSortedByReportsSizeRevers(pageable));
        return "eventsPage";
    }

    @GetMapping("/usersSize")
    public String eventsUsersSize(Model model,@PageableDefault(value = 10) Pageable pageable){
        model.addAttribute("eventList",eventService.getListOfEventsAfterCurrentSortedByUsersSize(pageable));
        return "eventsPage";
    }
    @GetMapping("/usersSizeDesc")
    public String eventsUsersSizeDesc(Model model,@PageableDefault(value = 10,direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("eventList",eventService.getListOfEventsAfterCurrentSortedByUsersSize(pageable));
        return "eventsPage";
    }
}

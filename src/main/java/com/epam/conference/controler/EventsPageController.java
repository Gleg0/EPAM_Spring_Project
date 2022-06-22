package com.epam.conference.controler;

import com.epam.conference.service.EventService;
import com.epam.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventsPageController {
    @Autowired
    EventService eventService;

    @GetMapping("/events")
    public String mainGet(Model model){
        model.addAttribute("eventList",eventService.getListOfEvents());
        return "eventsPage";
    }
}

package com.epam.conference.controler;

import com.epam.conference.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReportsPageController {
    @Autowired
    EventService eventService;

    @GetMapping("/reports")
    public String mainGet(Model model,@RequestParam Long eventId){
        model.addAttribute("reportList",eventService.getEventById(eventId).getReports());
        return "reportsPage";
    }
}

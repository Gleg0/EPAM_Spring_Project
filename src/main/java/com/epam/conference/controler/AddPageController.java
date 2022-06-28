package com.epam.conference.controler;

import com.epam.conference.entity.dto.EventDto;
import com.epam.conference.entity.dto.ReportDto;
import com.epam.conference.service.EventService;
import com.epam.conference.service.UserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/add")
@Controller
public class AddPageController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @ModelAttribute("event")
    public EventDto eventDto() {
        return new EventDto();
    }

    @ModelAttribute("report")
    public ReportDto reportDto() {
        return new ReportDto();
    }

    @GetMapping("/event")
    public String getAddEvent(Model model){
        model.addAttribute("type","event");
        return "addPage";
    }
    @GetMapping("/report")
    public String getAddReport(Model model, @RequestParam Long eventId, Session session){
        model.addAttribute("type","report");
        model.addAttribute("speakers",userService.allSpeakers());
        model.addAttribute("eventId",eventId);
        return "addPage";
    }
    @PostMapping("/event")
    public String postAddEvent(@ModelAttribute("event") @Valid EventDto eventDto){
        eventService.addNewEvent(eventDto);
        return "redirect:/events";
    }
    @PostMapping("/report")
    public String postAddReport(Model model,@ModelAttribute("report") @Valid ReportDto reportDto,@RequestParam(value = "eventId") Long eventId){
        eventService.addNewReport(eventId,reportDto);
        return "redirect:/events";
    }
}

package com.epam.conference.controler;

import com.epam.conference.entity.dto.EventDto;
import com.epam.conference.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/edit")
@Controller
public class EditPageController {

    @Autowired
    private EventService eventService;

    @ModelAttribute("event")
    public EventDto eventDto() {
        return new EventDto();
    }

    @GetMapping("/event")
    public String mainGetAddEvent(Model model){
        model.addAttribute("type","event");
        return "editPage";
    }
    @GetMapping("/report")
    public String mainGetAddReport(Model model){
        model.addAttribute("type","report");
        return "editPage";
    }
    @PostMapping("/event")
    public String mainPostAddEvent(@ModelAttribute("event") @Valid EventDto eventDto){
        eventService.addNewEvent(eventDto);
        return "editPage";
    }
    @PostMapping("/report")
    public String mainPostAddReport(@ModelAttribute("event") @Valid EventDto eventDto){

        return "editPage";
    }
}

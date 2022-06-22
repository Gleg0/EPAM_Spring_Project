package com.epam.conference.controler;

import com.epam.conference.entity.event.EventDto;
import com.epam.conference.entity.user.UserDto;
import com.epam.conference.service.EventService;
import com.epam.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.text.ParseException;

@RequestMapping("/add")
@Controller
public class AddPageController {

    @Autowired
    private EventService eventService;

    @ModelAttribute("event")
    public EventDto eventDto() {
        return new EventDto();
    }

    @GetMapping("/event")
    public String mainGetAddEvent(Model model){
        model.addAttribute("type","event");
        return "addPage";
    }
    @GetMapping("/report")
    public String mainGetAddReport(Model model){
        model.addAttribute("type","report");
        return "addPage";
    }
    @PostMapping("/event")
    public String mainPostAddEvent(@ModelAttribute("event") @Valid EventDto eventDto){
        try {
            eventService.addNewEvent(eventDto);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "addPage";
    }
    @PostMapping("/report")
    public String mainPostAddReport(@ModelAttribute("event") @Valid EventDto eventDto){

        return "addPage";
    }
}

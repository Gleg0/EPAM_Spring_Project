package com.epam.conference.controler;

import com.epam.conference.service.EventService;
import com.epam.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegForEventController {
    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;
    @GetMapping("/regForEvent")
    public String get(@RequestParam(name = "eventId") Long eventId,@AuthenticationPrincipal UserDetails currentUser){
        eventService.regForEvent(eventId,userService.findUserByName(currentUser.getUsername()));
        return "redirect:/events";
    }
}

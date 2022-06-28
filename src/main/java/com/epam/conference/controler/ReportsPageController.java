package com.epam.conference.controler;

import com.epam.conference.service.EventService;
import com.epam.conference.service.ReportService;
import com.epam.conference.service.RequestService;
import com.epam.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReportsPageController {
    @Autowired
    EventService eventService;
    @Autowired
    RequestService requestService;
    @Autowired
    UserService userService;
    @Autowired
    ReportService reportService;

    @GetMapping("/reports")
    public String mainGet(Model model,@RequestParam Long eventId,@AuthenticationPrincipal UserDetails currentUser){
        System.out.println(eventId);
        model.addAttribute("reportList",reportService.getAccepted(eventId));
        model.addAttribute("listsOfReqId",requestService.getListOfReqId(userService.findUserByName(currentUser.getUsername())));
        return "reportsPage";
    }
}

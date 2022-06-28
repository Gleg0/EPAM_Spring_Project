package com.epam.conference.controler;

import com.epam.conference.entity.dto.ReportDto;
import com.epam.conference.entity.event.RequestType;
import com.epam.conference.entity.event.UserRequest;
import com.epam.conference.service.EventService;
import com.epam.conference.service.ReportService;
import com.epam.conference.service.RequestService;
import com.epam.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class RequestPageController {
    @Autowired
    RequestService requestService;
    @Autowired
    UserService userService;
    @Autowired
    ReportService reportService;
    @Autowired
    EventService eventService;
    @ModelAttribute("report")
    public ReportDto reportDto() {
        return new ReportDto();
    }

    @GetMapping("/request")
    public String get(Model model,
                      @RequestParam(value = "eventId", required = false) Long eventId,
                      @RequestParam(value = "reportId", required = false) Long reportId,
                      @RequestParam(value = "type") String type,
                      @AuthenticationPrincipal UserDetails currentUser){
        if(type.equals("new_speaker")){
            UserRequest request = new UserRequest();
            request.setUser(userService.findUserByName(currentUser.getUsername()));
            request.setType(RequestType.New_Speaker);
            request.setReport(reportService.getReportById(reportId));
            requestService.addNewRequest(request);
            return "redirect:/events";
        }
        else if(type.equals("new_report")) {
            model.addAttribute("eventId",eventId);
            model.addAttribute("speakers",userService.allSpeakers());
            return "requestPage";
        }
        return "redirect:/events";
    }
    @PostMapping("/request")
    public String post(Model model,
                       @ModelAttribute("report") @Valid ReportDto reportDto,
                       @RequestParam(value = "eventId") Long eventId,
                       @AuthenticationPrincipal UserDetails currentUser){
        UserRequest userRequest = new UserRequest();
        userRequest.setReport(eventService.addNewReportRequest(eventId,reportDto));
        userRequest.setUser(userService.findUserByName(currentUser.getUsername()));
        userRequest.setType(RequestType.New_Report);
        requestService.addNewRequest(userRequest);
        return "redirect:/events";
    }
}

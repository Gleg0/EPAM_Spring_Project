package com.epam.conference.controler;

import com.epam.conference.service.EventService;
import com.epam.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequestMapping("/speaker")
@Controller
public class SpeakerPageController {

    @Autowired
    UserService userService;
    @Autowired
    EventService eventService;

    @GetMapping
    public String speakerGet(Model model, @PageableDefault(size = 10) Pageable pageable, @AuthenticationPrincipal UserDetails currentUser,
                         @RequestParam(value = "page",required = false) Optional<Integer> page){
        int currentPage = page.orElse(0);
        Page pageablePage = eventService.getListOfEventsForSpeaker(pageable,userService.findUserByName(currentUser.getUsername()));
        model.addAttribute("user",userService.findUserByName(currentUser.getUsername()));
        model.addAttribute("eventList",pageablePage);
        int totalPages = pageablePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "speakerPage";
    }

}

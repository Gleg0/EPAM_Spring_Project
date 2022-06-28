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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PastEventsPageController {
    @Autowired
    EventService eventService;

    @GetMapping("/before")
    public String mainGet(Model model,
                          @PageableDefault(size = 10) Pageable pageable,
                          @AuthenticationPrincipal UserDetails currentUser,
                          @RequestParam(value = "sort",required = false) Optional<String> sort,
                          @RequestParam(value = "page",required = false) Optional<Integer> page){
        Page pageablePage = eventService.getListOfEvents(pageable,"before");
        model.addAttribute("eventList",pageablePage);
        int totalPages = pageablePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "pastEventsPage";
    }
}

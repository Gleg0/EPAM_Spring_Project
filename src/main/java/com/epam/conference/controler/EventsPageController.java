package com.epam.conference.controler;

import com.epam.conference.entity.user.User;
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


@RequestMapping("/events")
@Controller
public class EventsPageController {
    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;

    @GetMapping
    public String events(Model model,
                         @PageableDefault(size = 10) Pageable pageable,
                         @AuthenticationPrincipal UserDetails currentUser,
                         @RequestParam(value = "sort",required = false) Optional<String> sort,
                         @RequestParam(value = "page",required = false) Optional<Integer> page,
                         @AuthenticationPrincipal User user){
        if(currentUser == null) return "loginPage";
        Optional.ofNullable(user).ifPresent(u->model.addAttribute("user", user));
        String sortType = sort.orElse("default");
        Page pageablePage = eventService.getListOfEvents(pageable,sortType);
        model.addAttribute("user",userService.findUserByName(currentUser.getUsername()));
        model.addAttribute("eventList",pageablePage);
        model.addAttribute("sort",sortType);
        int totalPages = pageablePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "eventsPage";
    }
}
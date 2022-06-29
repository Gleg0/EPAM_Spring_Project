package com.epam.conference.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginPageController {
    @GetMapping("/login")
    public String mainGet(){
        return "loginPage";
    }
    @PostMapping("/login")
    public String mainPost(){
        return "loginPage";
    }
}


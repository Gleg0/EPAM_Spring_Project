package com.epam.conference.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
public class MainPageController{
    @GetMapping("/")
    String mainGet(Model model, HttpSession session,Locale locale){
        return "mainPage";
    }
}

package com.epam.conference.controler;

import com.epam.conference.entity.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Optional;

@Controller
public class LoginPageController {
    @GetMapping("/login")
    public String mainGet(){
        return "loginPage";
    }
}

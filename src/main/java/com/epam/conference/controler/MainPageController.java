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
public class MainPageController{

    @GetMapping("/")
    public String mainGet(
            Model model,
            @AuthenticationPrincipal
                    User user,
            HttpSession session,
            Locale locale
    ){
        Optional.ofNullable(user).ifPresent(u->model.addAttribute("user", user));
        return "mainPage";
    }
}

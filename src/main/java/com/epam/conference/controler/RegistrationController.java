package com.epam.conference.controler;

import com.epam.conference.entity.User;
import com.epam.conference.entity.UserDto;
import com.epam.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
@Controller
@RequestMapping("/registration")
public class RegistrationController{
    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserDto userDto() {
        return new UserDto();
    }

    @GetMapping
    public String showRegistrationForm(WebRequest request, Model model) {
        return "registrationPage";
    }
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result) {
        User existing = userService.findUserByEmail(userDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            return "registrationPage";
        }
        userService.registerNewUserAccount(userDto);
        return "redirect:/registrationPage?success";
    }
}

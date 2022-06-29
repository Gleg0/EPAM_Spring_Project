package com.epam.conference.controler;

import com.epam.conference.entity.dto.UserDto;
import com.epam.conference.entity.user.User;
import com.epam.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    private UserDto userDto() {
        return new UserDto();
    }

    @GetMapping
    public String showRegistrationForm(WebRequest request, Model model) {
        return "registrationPage";
    }
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result) {
        User existingEmail = userService.findUserByEmail(userDto.getEmail());
        User existingUsername = userService.findUserByName(userDto.getUsername());
        if (existingEmail != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (existingUsername != null) {
            result.rejectValue("username", null, "There is already an account registered with that username");
        }
        if (result.hasErrors()) {
            return "registrationPage";
        }
        userService.registerNewUserAccount(userDto);
        return "redirect:/login";
    }
}

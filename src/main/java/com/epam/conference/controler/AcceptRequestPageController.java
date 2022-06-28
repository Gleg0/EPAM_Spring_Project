package com.epam.conference.controler;

import com.epam.conference.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class AcceptRequestPageController {
    @Autowired
    RequestService requestService;

    @GetMapping("/acceptRequestList")
    public String get(Model model){
        model.addAttribute("requestList",requestService.getAllRequest());
        return "acceptRequestPage";
    }
    @GetMapping("/acceptRequest")
    public String getR(Model model, @RequestParam Long requestId,@RequestParam(value = "reject",required = false) String reject){
        if(reject == null){
            requestService.accept(requestId);
        }
        else {
            requestService.reject(requestId);
        }
        return "redirect:/acceptRequestList";
    }
}

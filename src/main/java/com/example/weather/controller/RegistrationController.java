package com.example.weather.controller;

import com.example.weather.dto.RegisterUserRequest;
import com.example.weather.dto.RegisterUserResponse;
import com.example.weather.service.RegistrationService;
import com.example.weather.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class RegistrationController {

    private final RegistrationService userService;
    private final SessionService sessionService;

    public RegistrationController(RegistrationService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @GetMapping(value = "/signUp")
    public ModelAndView register(){
        return new ModelAndView("sign-up");
    }

    @PostMapping(value = "/signUp")
    public ModelAndView register(@ModelAttribute("registerUserRequest") RegisterUserRequest registerUserRequest,
                                 @RequestParam("redirect_to") String redirect_to) {
        System.out.println(registerUserRequest);
        final RegisterUserResponse register = userService.register(registerUserRequest);
        return new ModelAndView(new RedirectView(redirect_to));
    }
}

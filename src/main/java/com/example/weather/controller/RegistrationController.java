package com.example.weather.controller;

import com.example.weather.dto.RegisterUserRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.weather.service.RegistrationService;

//@ControllerAdvice


@Controller
public class RegistrationController {

    private final RegistrationService userService;

    public RegistrationController(RegistrationService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute("registerUserRequest") RegisterUserRequest registerUserRequest,
                           @RequestParam("redirect_to") String redirect_to) {
        userService.register(registerUserRequest);

        return "redirect:" + redirect_to;
    }


}

package com.example.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RootController {

    @GetMapping(value = "/")
    public ModelAndView root(ModelAndView model) {
        return new ModelAndView(new RedirectView("/signIn"));
    }
}

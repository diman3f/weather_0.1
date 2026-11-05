package com.example.weather.controller;

import com.example.weather.dto.WeatherLocationResponse;
import com.example.weather.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping(value = "/index")
    public ModelAndView home(ModelAndView modelAndView, @ModelAttribute("user") User user,
                             @RequestAttribute("userId") Long userId) {

        List<String> location = List.of("Samara", "Moscow", "Penza");
        List<WeatherLocationResponse> weather = List.of(new WeatherLocationResponse("Samara", "SA", +15),
                new WeatherLocationResponse("Moscow", "MOS", +18), new WeatherLocationResponse("Penza", "PZ", +25));
        modelAndView.addObject("location", location);
        modelAndView.addObject("weather", weather);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}

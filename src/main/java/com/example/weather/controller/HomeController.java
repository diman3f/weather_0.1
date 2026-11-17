package com.example.weather.controller;

import com.example.weather.dto.LocationDeleteRequest;
import com.example.weather.dto.WeatherDto;
import com.example.weather.dto.WeatherLocationResponse;
import com.example.weather.entity.User;
import com.example.weather.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/home")
public class HomeController {
    //todo явно не ответственность сервиса локаций собирать карточку погоды для клиента
    // по сигнатурам методов видно что у него больше одной ответственноси
    private LocationService locationService;

    public HomeController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping(value = "/index")
    public ModelAndView home(ModelAndView modelAndView, @ModelAttribute("user") User user,
                             @RequestAttribute("userId") Long userId) {

        //todo наличие dto в коллекции нужно проверять на слое сервиса в контроллера должны приходить
        // готовые данные для отображения
        final List<WeatherDto> weathers = locationService.searchWeatherByUserId(userId);
        modelAndView.addObject("weathers", weathers);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping(value = "/delete")
    public ModelAndView delete(@ModelAttribute LocationDeleteRequest dto, @RequestAttribute("userId") Long userId) {
        final List<WeatherDto> weatherUser = locationService.deleteLocation(dto, userId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("weathers", weatherUser);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}

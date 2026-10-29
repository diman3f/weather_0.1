package com.example.weather.controller;

import com.example.weather.dto.LocationDto;
import com.example.weather.dto.TestLocDto;
import com.example.weather.service.LocationService;
import io.micrometer.common.lang.internal.Contract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "location")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping(value = "/search")
    public ModelAndView search(ModelAndView model, @RequestParam("name") String name) {

        final List<LocationDto> locations = locationService.searchByName(name);
        model.addObject("locations", locations);
        model.setViewName("search-results");
        return model;
    }




}


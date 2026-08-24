package controller;

import dto.WeatherLocationResponse;
import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {



    @RequestMapping(value = "/index", method = RequestMethod.GET)

    public ModelAndView home(ModelAndView modelAndView,  @ModelAttribute("user") User user) {
        List<String> location = List.of("Samara", "Moscow", "Penza");
        List<WeatherLocationResponse> weather = List.of(new WeatherLocationResponse("Samara", "SA", +15),
                new WeatherLocationResponse("Moscow", "MOS", +18), new WeatherLocationResponse("Penza", "PZ", +25));
        modelAndView.addObject("location", location);
        modelAndView.addObject("weather", weather);
        modelAndView.setViewName("index");
        return modelAndView;
    }


}

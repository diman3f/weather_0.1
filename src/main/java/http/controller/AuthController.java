package http.controller;

import dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AuthController {

    @RequestMapping(value = "/signIn")
    public ModelAndView signIn(@RequestParam("username") String name, @RequestParam("password") String password,
                         @RequestParam("redirect_to") String url, ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        modelAndView.addObject("user",new UserReadDto(1L, name));
        return modelAndView;

    }

    @RequestMapping(value = "/signInOpen")
    public ModelAndView openSignIn(ModelAndView modelAndView) {
        modelAndView.setViewName("sign-in");
        return modelAndView;
    }

}

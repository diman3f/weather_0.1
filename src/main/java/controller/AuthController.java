package controller;

import dto.AuthUserDto;
import dto.UserReadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.AuthUserService;


@Controller
public class AuthController {


    private final AuthUserService service;

    public AuthController(AuthUserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/signIn")
    public ModelAndView signIn(@RequestParam("username") String name, @RequestParam("password") String password,
                         @RequestParam("redirect_to") String url, ModelAndView modelAndView) {

        final AuthUserDto authUser = service.auth(new UserReadDto(1L, name));

        modelAndView.setViewName("index");
        modelAndView.addObject("user",authUser);
        return modelAndView;

    }

    @RequestMapping(value = "/signInOpen")
    public ModelAndView openSignIn(ModelAndView modelAndView) {
        modelAndView.setViewName("sign-in");
        return modelAndView;
    }

}

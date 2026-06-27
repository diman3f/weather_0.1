package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String process(ModelAndView modelAndView, @RequestParam  ("username") String login,
                                @RequestParam("password") String password, @RequestParam("redirect_to") String url) {
        modelAndView.addObject(login);
        modelAndView.addObject(password);

        return "redirect:" + url;
    }




}

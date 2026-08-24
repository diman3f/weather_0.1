package controller;

import dto.AuthUserDto;
import dto.AuthUserResponse;
import dto.UserDto;
import dto.UserReadDto;
import entity.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.AuthUserService;
import java.util.UUID;


@Controller
@ControllerAdvice
public class AuthController {
    private final AuthUserService service;

    public AuthController(AuthUserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/signIn")
    public String signIn(@RequestParam("username") String name, @RequestParam("password") String password,
                         @RequestParam("redirect_to") String url, Model model, HttpServletResponse response) {
        final AuthUserResponse authUserResponse = service.authenticateUser(name, password);
        final Cookie cookie = new Cookie("uuid", String.valueOf(authUserResponse.idUserSession()));
        cookie.setMaxAge(30 * 30);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        System.out.println("hello");
        model.addAttribute(authUserResponse);
        return "redirect:index";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg", "Welcome to the Netherlands!");
    }

    @RequestMapping(value = "/signInOpen")
    public ModelAndView openSignIn(ModelAndView modelAndView) {
        modelAndView.setViewName("sign-in");
        return modelAndView;
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(@RequestAttribute("uuid") String id, ModelAndView modelAndView) {
        final UUID uuid = UUID.fromString(id);
        service.invalidateUser(uuid);
        modelAndView.setViewName("sign-in");
        return modelAndView;
    }

}

package com.example.weather.controller;

import com.example.weather.dto.AuthUserRequest;
import com.example.weather.dto.AuthUserResponse;
import com.example.weather.service.AuthUserService;
import com.example.weather.service.AuthenticationCookieBuilder;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@Controller
public class AuthController {
    private final AuthUserService service;
    private final AuthenticationCookieBuilder cookieBuilder;

    public AuthController(AuthUserService service, AuthenticationCookieBuilder cookieBuilder) {
        this.service = service;
        this.cookieBuilder = cookieBuilder;
    }

    @GetMapping(value = "/signIn")
    public ModelAndView openSignIn() {
        return new ModelAndView("sign-in");
    }

    @PostMapping(value = "/signIn")
    public ModelAndView signIn(@ModelAttribute AuthUserRequest dto, @RequestParam("redirect_to") String redirect_to, HttpServletResponse response) {
        final AuthUserResponse authUserResponse = service.authenticateUser(dto.username(), dto.password());
        if (authUserResponse.uuidSession() != null)
            cookieBuilder.setCookieResponse(response, authUserResponse.uuidSession(), "uuid");
        return new ModelAndView(new RedirectView(redirect_to));
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg", "Welcome to the Netherlands!");
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(@RequestAttribute("uuid") String id, ModelAndView modelAndView) {
        final UUID uuid = UUID.fromString(id);
        service.invalidateUser(uuid);
        modelAndView.setViewName("sign-in");
        return modelAndView;
    }
}

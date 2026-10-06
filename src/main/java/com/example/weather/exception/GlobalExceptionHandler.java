package com.example.weather.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice("com/example/weather/controller")
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handlerGenericException(Exception ex, HttpServletResponse response) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        response.setHeader("code", "12332");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }


    @ExceptionHandler(SessionExpiredException.class)
    public ModelAndView handlerSessionExpired(Exception ex, HttpServletResponse response) {
        System.out.println("handlerGenericException working!");
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        response.setHeader("code", "12332");

        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ModelAndView handlerAuthenticationException(AuthenticationCredentialsNotFoundException ex) {
        log.error("Failed to authenticate user :{}", ex.getMessage(), ex);
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sign-in-with-errors");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.addObject("user", ex.getUserName());
        return modelAndView;
    }
}

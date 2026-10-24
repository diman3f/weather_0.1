package com.example.weather.service;

import com.example.weather.entity.Session;
import com.example.weather.entity.User;
import com.example.weather.exception.SessionExpiredException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
@Transactional
public class CookieHandlerInterceptor implements HandlerInterceptor {

    private SessionService service;

    public CookieHandlerInterceptor(SessionService service) {
        this.service = service;
    }

   @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        final Optional<UUID> uuid = getUuid(request);
        if (uuid.isPresent()) {
            try {
                final Session validSession = service.getValidSession(uuid.get());
                final Long userId = validSession.getUser().getId();
                request.setAttribute("userId", userId);
                request.setAttribute("hello", String.format("The session expires at %d, minutes %d",
                        validSession.getExpiresAt().getHour(), validSession.getExpiresAt().getMinute()));
                final Session prolongate = service.prolongate(validSession);
                request.setAttribute("prolongate", String.format("The prolongate session expires at %d, minutes %d",
                        prolongate.getExpiresAt().getHour(), prolongate.getExpiresAt().getMinute()));

            } catch (SessionExpiredException e) {
                System.out.println(String.format("Cookies равен %s", String.valueOf(uuid.get())));
                request.setAttribute("user", new User("ivan", "123"));
                request.setAttribute("hello", e);
            }
            return true;
        }
        throw new RuntimeException("uuid отсутствует");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        final Map<String, Object> model = modelAndView.getModel();
        final Object uuid = model.get("uuid");
        response.addCookie(new Cookie("id", String.valueOf(uuid)));
        System.out.println();

    }

    private Optional<UUID> getUuid(HttpServletRequest request) {
        final Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("uuid")) {
                return Optional.of(UUID.fromString(cookie.getValue()));
            }
        }
        return Optional.empty();
    }
}

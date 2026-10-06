package com.example.weather.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import com.example.weather.service.CookieHandlerInterceptor;
import com.example.weather.service.SessionService;

@Configuration
@ComponentScan("com.example.weather.controller")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {


    private final ApplicationContext context;

    public WebConfig(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.context);
        templateResolver.setSuffix(".html");
        templateResolver.setPrefix("/WEB-INF/views/");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }
}

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(cookieHandlerInterceptor()).excludePathPatterns("/signIn", "/signInOpen", "/","/register");
//    }
//
//    @Bean
//  //  public HandlerInterceptor cookieHandlerInterceptor() {
//        return new CookieHandlerInterceptor(context.getBean(SessionService.class));
//    }
//}

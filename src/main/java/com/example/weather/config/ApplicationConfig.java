package com.example.weather.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("com.example.weather.controller")
@ComponentScan("com.example.weather.service")
@ComponentScan("com.example.weather.repository")
@ComponentScan(basePackages = "com.example.weather.exception")
public class ApplicationConfig {


}

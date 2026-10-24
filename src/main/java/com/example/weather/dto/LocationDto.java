package com.example.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class LocationDto {

    private Main main;
    private String name;
    private List<Weather> weather;
    private Sys sys;


    @Getter
    @Setter
    @ToString
    static class Main {
        private Double temp;
        @JsonProperty("feels_like")
        private Double feelsLike;
        private Integer humidity;
    }

    @Getter
    @Setter
    @ToString
    static class Weather {
        private String main;
        private String description;
        private String icon;
    }

    @Getter
    @Setter
    @ToString
    static class Sys {
        private String country;
    }
}


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
public class WeatherDto {
    private Long id;
    private Location location;
    private Current current;


    @Getter
    @Setter
    @ToString
    public static class Location {
        private String name;
        private String country;
        private Double lat;
        private Double lon;
    }

    @Getter
    @Setter
    @ToString
    public static class Current {
        private Double temp_c;

        @JsonProperty("feelslike_c")
        private Double feelsLike;

        private Integer humidity;

        private Condition condition;

        @Getter
        @Setter
        @ToString
        public static class Condition {
            private String text;
            private String icon;
        }
    }

}
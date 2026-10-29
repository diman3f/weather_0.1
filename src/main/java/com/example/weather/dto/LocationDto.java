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
    private String name;
    @JsonProperty("local_names")
    private LocalName localName;
    private Double lat;
    private Double lon;
    private String country;
    private String state;


    @Getter
    @Setter
    @ToString
    static class LocalName {
        String ru;
    }

}


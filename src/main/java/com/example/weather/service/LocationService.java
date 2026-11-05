package com.example.weather.service;

import com.example.weather.dto.AddLocationRequest;
import com.example.weather.dto.LocationDto;
import com.example.weather.entity.Location;
import com.example.weather.repository.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class LocationService {
    //api key 40ab87c7244af7c7389f0ec327868a2f
    //40ab87c7244af7c7389f0ec327868a2f
    public static String geoCoding = "http://api.openweathermap.org/geo/1.0/direct?q=%s,ru&limit=8&appid=40ab87c7244af7c7389f0ec327868a2f";
    public static String getLocationByName = "https://api.openweathermap.org/data/2.5/weather?q=Samara&units=metric&appid=40ab87c7244af7c7389f0ec327868a2f";

    public static String getWeatherByCoordinates = "https://api.openweathermap.org/data/4.0/onecall/current?lat=%s&lon=%s&appid=40ab87c7244af7c7389f0ec327868a2f";

    private Repository<Long, Location> repository;

    public LocationService(Repository<Long, Location> repository) {
        this.repository = repository;
    }


    public List<LocationDto> searchByName(String name) {
        RestClient client = RestClient.create();
        String uri = String.format(geoCoding, name);
        final String jsonStr = client.get()
                .uri(uri)
                .retrieve()
                .body(String.class);
        ObjectMapper mapper = new ObjectMapper();
        final List<LocationDto> locations = mapper.readValue(jsonStr, new TypeReference<List<LocationDto>>() {
        });
        return locations;
    }

    @Transactional
    public void add(AddLocationRequest dto, Long userId) {
        final Double latitude = dto.lat();
        final Double longitude = dto.lon();

        Location location = new Location(dto.name(),userId, latitude, longitude);

        repository.save(location);

        }

}

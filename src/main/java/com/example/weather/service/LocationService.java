package com.example.weather.service;

import com.example.weather.dto.AddLocationRequest;
import com.example.weather.dto.LocationDeleteRequest;
import com.example.weather.dto.LocationDto;
import com.example.weather.dto.WeatherDto;
import com.example.weather.entity.Location;
import com.example.weather.exception.LocationByUserIdNotFound;
import com.example.weather.repository.JpaLocationRepository;
import com.example.weather.repository.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {
    //api key 40ab87c7244af7c7389f0ec327868a2f
    // bd447cefcd5642aebe2174536260911 weather API
    //"https://api.weatherapi.com/v1/current.json?key=bd447cefcd5642aebe2174536260911&q=London&aqi=yes"
    public static String geoCoding = "http://api.openweathermap.org/geo/1.0/direct?q=%s,ru&limit=8&appid=40ab87c7244af7c7389f0ec327868a2f";
    public static String getLocationByName = "https://api.openweathermap.org/data/2.5/weather?q=Samara&units=metric&appid=40ab87c7244af7c7389f0ec327868a2f";

    public static String getWeatherByCoordinates = "https://api.weatherapi.com/v1/current.json?key=bd447cefcd5642aebe2174536260911&q=%s,%s";

    private final LocationRepository repository;

    public LocationService(JpaLocationRepository repository) {
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

    public List<WeatherDto> searchWeatherByUserId(Long userId) {
        final List<Location> locationByUserId = getByUserId(userId);
        List<WeatherDto> weatherDtoList = new ArrayList<>();
        if (!locationByUserId.isEmpty()) {
            for (Location location : locationByUserId) {
                final WeatherDto weatherDto = searchByCoordinate(location.getLatitude(), location.getLatitude());
                weatherDto.setId(location.getId());
                weatherDtoList.add(weatherDto);
            }
        } else throw new LocationByUserIdNotFound("Location by userId not found");
        return weatherDtoList;
    }

    //todo тут возникает LazyInitializationException по полю users
    //todo обернуть исключение
    private List<Location> getByUserId(Long userId) {
        return repository.getAllLocationByUserId(userId);
    }

    private WeatherDto searchByCoordinate(Double lat, Double lon) {
        RestClient client = RestClient.create();
        String uri = String.format(getWeatherByCoordinates, lat, lon);
        final String jsonStr = client.get()
                .uri(uri)
                .retrieve()
                .body(String.class);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonStr, WeatherDto.class);
    }
    //todo обернуть в optional


    @Transactional
    //todo обработка ошибки удаления
    //todo добавил ид чтобы вернуть карточки погоды после удаления
    //todo после удаления снова делаем цикл запросов к апи по хорошему нужен кеш хотябы запросов на 5
    //todo обернуть в Optional
    public List<WeatherDto> deleteLocation(LocationDeleteRequest dto, Long userId) {
        repository.deleteLocation(dto.id());
        final List<WeatherDto> weathers = searchWeatherByUserId(userId);
        return weathers;
    }

    @Transactional
    public void add(AddLocationRequest dto, Long userId) {
        final Double latitude = dto.lat();
        final Double longitude = dto.lon();
        Location location = new Location(dto.name(), userId, latitude, longitude);
        repository.save(location);
    }


}

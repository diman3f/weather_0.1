package org;

import com.example.weather.dto.LocationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
public class RestTemplateTest {
//api key 40ab87c7244af7c7389f0ec327868a2f
    public static String getLocationByName = "https://api.openweathermap.org/data/2.5/weather?q=Samara&units=metric&appid=40ab87c7244af7c7389f0ec327868a2f";

    @Test
    public void givenUriWhenRequestHttpClientThenValidResponse(){

        RestClient client = RestClient.create();
        final String jsonStr = client.get()
                .uri(getLocationByName)
                .retrieve()
                .body(String.class);
        ObjectMapper mapper = new ObjectMapper();

        final LocationDto locationDto = mapper.readValue(jsonStr, LocationDto.class);

        System.out.println(locationDto);

        System.out.println();
        System.out.println();

        System.out.println(jsonStr);

    }
    @Test
    public void givenUriCityWhenRequestByCoordinateThenValidResponseCity(){
        RestClient client = RestClient.create();

            final String body = client.get()
                    .uri("http://api.openweathermap.org/geo/1.0/direct?q=Samara,ru&limit=5&appid=40ab87c7244af7c7389f0ec327868a2f")
                    .retrieve()
                    .body(String.class);
            System.out.println(body);
        }

    }



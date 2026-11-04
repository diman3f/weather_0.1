package org;

import com.example.weather.config.ApplicationConfig;
import com.example.weather.config.FlyWayConfig;
import com.example.weather.config.JpaConfig;
import com.example.weather.config.WebConfig;
import com.example.weather.entity.Location;
import com.example.weather.repository.JpaLocationRepository;
import com.example.weather.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfig.class, WebConfig.class, JpaConfig.class, FlyWayConfig.class})
@WebAppConfiguration
public class LocationControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Autowired
    private JpaLocationRepository repository;


    @BeforeEach
    public void configuration() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void givenUserId_WhenFindLocationsByUserId_ThenReturnListLocation(){
        Long userId = 64L;
        final List<Location> allLocationByUserId = repository.getAllLocationByUserId(userId);
        Assertions.assertEquals(allLocationByUserId.getFirst().getId(), 3);
        Assertions.assertEquals(allLocationByUserId.getLast().getId(), 5);
        Assertions.assertNotNull(allLocationByUserId);
    }


}

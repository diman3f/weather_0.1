package org;

import com.example.weather.controller.RegistrationController;
import com.example.weather.dto.RegisterUserRequest;
import com.example.weather.org.NoValidLoginException;
import com.example.weather.service.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegistrationControllerTest {
    private RequestBuilderMockMvc builderMockMvc;
    private RegistrationService service;


    @BeforeEach
    void configurationTest() {

        service = mock(RegistrationService.class);

        RegistrationController registrationController = new RegistrationController(service);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(registrationController)
                .setViewResolvers(WebConfigTest.viewResolver())
                .setHandlerExceptionResolvers(WebConfigTest.exceptionResolver())
                .build();
        this.builderMockMvc = new RequestBuilderMockMvc(mockMvc);
    }

    @Nested
            //@DisplayName()
    class RegistrationUserNoValidLogin {

        private final String NoValidLogin = "User23";
        private final String something = " ";
        RegisterUserRequest request = new RegisterUserRequest(NoValidLogin, something, something);

        @Nested
        class WhenRequestRegistrationNotValid {

            //метод стабит мок сервиса чтобы тот возращал userNotFound при не валидном логине в реквесте
            @BeforeEach
            void serviceThrowNoValidLoginException() {
                given(service.register(request)).
                        willThrow(new NoValidLoginException());
            }

            @Test
            void shouldReturnHttpStatusNotFound() {
                final ResultActions register = builderMockMvc.register(request);
                try {
                    register.andExpect(status().isNotFound());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

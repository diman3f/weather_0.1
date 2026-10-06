package org;

import com.example.weather.dto.RegisterUserRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class RequestBuilderMockMvc {
    private MockMvc mockMvc;

    public RequestBuilderMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public ResultActions register(RegisterUserRequest dto) {


        final MockHttpServletRequestBuilder register = MockMvcRequestBuilders.post("/register");
        try {
            return mockMvc.perform(
                    register.param("username", dto.username())
                            .param("password", dto.password())
                            .param("redirect_to", "123"));

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}

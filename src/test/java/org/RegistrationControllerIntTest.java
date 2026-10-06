package org;

import com.example.weather.config.ApplicationConfig;
import com.example.weather.config.FlyWayConfig;
import com.example.weather.config.JpaConfig;
import com.example.weather.config.WebConfig;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.RollbackOn;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfig.class, WebConfig.class, JpaConfig.class, FlyWayConfig.class})
@WebAppConfiguration()
public class RegistrationControllerIntTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;


    //бизнес действие регистрация пользователя

    //реализация действия
    // сервис регистрации получает dto с данными для регистрации отваледированные
    // сервис извлекает поля пароля и шифрует его
    // сервис создает ентити логин + пароль для передачи на репозиторий
    // репозиторий выполняет сохраняет сущность
    // репозиторий отправляет entity c присвоенным id

    //возможные сценарии теста
    // данные логин не повторяется - успешная регистрация
    // логин уже есть в бд - ошибка регистрации изменения системы нет
    // репозиторий не доступен - ошибка регистрации


    @BeforeEach
    void configuration() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenGetNameController_WhenMockMvcReturnNameController() {
        final ServletContext servletContext = webApplicationContext.getServletContext();
        Assertions.assertNotNull(servletContext);

    }

    @Test
    public void givenHomePageURI_whenMockMVC_thenReturnsIndexJSPViewName() {
        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/index");
        try {
            this.mockMvc.perform(requestBuilder)
                    .andExpect(view().name("index"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void givenRootURI_whenGetRequestURI_thenReturnsRootNamePage() {
        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/root");
        try {
            this.mockMvc.perform(requestBuilder)
                    .andExpect(view().name("sign-up"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    @Transactional
    public void handlerRegisterUser_ReturnsValidResponseUserDto() {
        // given
        String name = "UserReg33334";
        String password = "somepassword123334";
        String redirect_to = "/index";


        try {
            final ResultActions perform = mockMvc.perform(post("/register")
                    .param("username", name)
                    .param("password", password)
                    .param("redirect_to", redirect_to))
                    .andExpect(status().isFound())
                    .andExpect(redirectedUrl("/index"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }




    }

}

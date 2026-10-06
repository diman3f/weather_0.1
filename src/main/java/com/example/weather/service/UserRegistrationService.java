package com.example.weather.service;

import com.example.weather.dto.RegisterUserRequest;
import com.example.weather.dto.RegisterUserResponse;
import com.example.weather.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.weather.repository.Repository;

@Service
@Transactional
@Slf4j
public class UserRegistrationService implements RegistrationService{
    private Repository<Long, User> repository;

    public UserRegistrationService(Repository<Long, User> repository) {
        this.repository = repository;
    }


    //поличить данные юзер и пароль и повторный пароль
    // если ок захешировать пароль
    // генерация salt
    // создать сущность и добавить хешированный пароль
    // выполнить попытку сохранения сущности
    // если все ок то юзер зарегестрирован можно редиректнуть его на форму авторизации

    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        final String name = registerUserRequest.username();
        final String password = registerUserRequest.password();
//        String hashedPassword = generateHashPassword(password);
//        System.out.println(hashedPassword);
        User user = new User(name, password);
        final User saveUser = repository.save(user);
        log.error("Error registration");
        return new RegisterUserResponse(saveUser.getId(), saveUser.getLogin());

    }

    private String generateHashPassword(String password) {
        //соль генерируется автоматически для каждого password будет своя соль
        return BCrypt.hashpw(password, BCrypt.gensalt());

    }
}

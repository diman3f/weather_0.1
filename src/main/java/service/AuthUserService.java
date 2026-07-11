package service;

import dao.Dao;
import dto.AuthUserDto;
import dto.UserReadDto;
import entity.User;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {

    private final SessionFactory sessionFactory;
    private final Flyway flyway;
    private final Dao<Long, User> dao;

    public AuthUserService(SessionFactory sessionFactory, Flyway flyway, Dao<Long, User> dao) {
        this.sessionFactory = sessionFactory;
        this.flyway = flyway;
        this.dao = dao;

    }

    public AuthUserDto auth(UserReadDto user) {
        final String database = flyway.migrate().schemaName;
        User userAuth = new User(user.getName());

        dao.save(userAuth);
        return new AuthUserDto(userAuth.getId(), userAuth.getLogin(),"OK");

    }
}


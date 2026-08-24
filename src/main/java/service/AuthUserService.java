package service;

import dto.AuthUserDto;
import dto.AuthUserResponse;
import dto.UserReadDto;
import entity.Session;
import entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.Repository;
import repository.UserRepository;
import repository.UserRepositoryImp;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AuthUserService {

    private final Repository<Long, User> repository;
    private final UserRepository userRepository;
    private final SessionService sessionService;


    public AuthUserService(UserRepositoryImp repository, SessionService sessionService) {
        this.repository = repository;
        this.userRepository = repository;
        this.sessionService = sessionService;

    }

    //todo перенеси этот метод в регистрацию
    public AuthUserDto auth(UserReadDto user) {
        User userAuth = new User(user.getName(), user.getPassword());
        repository.save(userAuth);
        return new AuthUserDto(userAuth.getId(), userAuth.getLogin(), "OK");
    }

    public void invalidateUser(UUID id){
        sessionService.invalidateSession(id);
    }

    public AuthUserResponse authenticateUser(String login, String password) {
        final Optional<User> entity = userRepository.findByLoginUser(login);
        if (entity.isPresent()) {
            final User user = entity.get();
            final Session session = sessionService.create(user);
            System.out.println(session.getExpiresAt());
            return new AuthUserResponse(user.getId(), user.getLogin(), session.getId());
        }
        throw new EntityNotFoundException(String.format("Пользователь %s не зарегестрирован",login));
    }
}


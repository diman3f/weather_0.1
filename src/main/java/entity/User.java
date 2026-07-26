package entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "users", indexes = @Index(columnList = "login"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password; // по тз нельзя хранить пароль в открытом виде нужно использовать
    // BCrypt
    @ManyToMany(mappedBy = "users")
    private List<Location> locations;

    @OneToMany(mappedBy = "user")
    private List<Session> sessions;

    public User(String login, String password) {
        this.id = null;
        this.login = login;
        this.password = password;
    }
}

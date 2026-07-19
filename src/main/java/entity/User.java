package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password; // по тз нельзя хранить пароль в открытом виде нужно использовать
    // BCrypt

    protected User() {
    }

    public User(String login) {
        this.id = null;
        this.login = login;
        this.password = "secret";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }


}

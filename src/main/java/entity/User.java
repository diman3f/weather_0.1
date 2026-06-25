package entity;

public class User {
    private Long id;
    private String login;
    private String password; // по тз нельзя хранить пароль в открытом виде нужно использовать
    // BCrypt

    protected User() {
    }

}

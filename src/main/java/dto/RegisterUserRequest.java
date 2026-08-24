package dto;

public record RegisterUserRequest(Long id ,String username, String password, String repeatPassword) {
}

package dto;

import lombok.Getter;

@Getter
public class UserReadDto {
    private String name;
    private String password;

    public UserReadDto(String name, String password) {

        this.name = name;
        this.password = password;
    }

}

package dto;

public class UserReadDto {
    private Long id;
    private String name;

    public UserReadDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

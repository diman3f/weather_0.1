package dto;

public class AuthUserDto {

    private Long id;
    private String name;
    private String authOK;

    public AuthUserDto(Long id, String name, String authOK) {
        this.id = id;
        this.name = name;
        this.authOK = authOK;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AuthUserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authOK='" + authOK + '\'' +
                '}';
    }
}



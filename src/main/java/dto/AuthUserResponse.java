package dto;

import java.util.UUID;

public record AuthUserResponse(Long id, String name, UUID idUserSession) {
}

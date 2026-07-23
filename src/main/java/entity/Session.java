package entity;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.UUID;

@Entity
public class Session {
    @Id
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User userSession;
    @Column(name = "expires_At", nullable = false)
    private LocalTime expiresAt;

}

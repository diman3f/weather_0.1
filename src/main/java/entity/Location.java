package entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "location",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uniq_lat_lon",
                        columnNames = {"latitude", "longitude"})},
        indexes = @Index(name = "ind_user", columnList = "user_id"))
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false)
    private BigDecimal latitude;
    @Column(nullable = false)
    private BigDecimal longitude;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id", nullable = false)
    private User userLocation;

}

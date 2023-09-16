package by.element.app.models.avatar;

import by.element.app.models.user.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "avatar")
public class Avatar {

    @Id
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId
    private Users user;

    @Column(name = "path", unique = true)
    private String path;
}

package by.element.app.models.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "authentication_data")
public class AuthenticationData {

    @Id
    @Column(name = "users_id", nullable = false)
    private UUID usersId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    private Users users;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;
}

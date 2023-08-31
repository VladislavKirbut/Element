package by.element.elementapp.models.user;

import by.element.elementapp.models.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "users")
public class Users extends BaseEntity {

    @OneToOne(mappedBy = "users", optional = false, cascade = CascadeType.ALL)
    private AuthenticationData authenticationData;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public Users setAuthenticationData(AuthenticationData authenticationData) {
        this.authenticationData = authenticationData;
        authenticationData.setUsers(this);
        return this;
    }
}

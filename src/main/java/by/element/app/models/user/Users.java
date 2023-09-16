package by.element.app.models.user;

import by.element.app.models.BaseEntity;
import by.element.app.models.avatar.Avatar;
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

    @OneToOne(mappedBy = "user", optional = false, cascade = CascadeType.ALL)
    private Avatar avatar;

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

    public Users setAvatar(Avatar avatar) {
        this.avatar = avatar;
        avatar.setUser(this);
        return this;
    }
}

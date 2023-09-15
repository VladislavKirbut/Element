package by.element.app.models.security;

import by.element.app.models.user.Users;
import lombok.Value;

import java.util.UUID;

@Value
public class UserPrincipal implements AccountPrincipal {
    UUID id;

    @Override
    public AccountRole getRole() {
        return AccountRole.USER;
    }

    public static UserPrincipal from(Users user) {
        return new UserPrincipal(user.getId());
    }
}

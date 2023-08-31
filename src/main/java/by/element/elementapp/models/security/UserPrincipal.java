package by.element.elementapp.models.security;

import by.element.elementapp.models.user.Users;
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

package by.element.app.models.security;

import java.util.UUID;

public interface AccountPrincipal {

    UUID getId();

    AccountRole getRole();
}

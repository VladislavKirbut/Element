package by.element.elementapp.models.security;

import java.util.UUID;

public interface AccountPrincipal {

    UUID getId();

    AccountRole getRole();
}

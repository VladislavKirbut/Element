package by.element.app.service.authentication;

import by.element.app.models.security.AccessToken;
import by.element.app.models.security.AccountPrincipal;

public interface AccessTokenService {
    AccessToken generateToken(AccountPrincipal accountPrincipal);

    AccountPrincipal authenticate(String tokenValue);
}

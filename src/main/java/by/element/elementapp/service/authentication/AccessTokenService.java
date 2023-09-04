package by.element.elementapp.service.authentication;

import by.element.elementapp.models.security.AccessToken;
import by.element.elementapp.models.security.AccountPrincipal;

public interface AccessTokenService {
    AccessToken generateToken(AccountPrincipal accountPrincipal);

    AccountPrincipal authenticate(String tokenValue);
}

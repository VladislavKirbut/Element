package by.element.elementapp.service.authentication;

import by.element.elementapp.exception.authentication.AuthenticationException;
import by.element.elementapp.models.user.AuthenticationDto;
import by.element.elementapp.models.user.AuthenticationSignInDto;

public interface AuthenticationService {
    AuthenticationDto isUserAuthenticated(AuthenticationSignInDto authorizationData) throws AuthenticationException;
}

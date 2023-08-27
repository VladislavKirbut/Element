package by.element.elementapp.service.authentication;

import by.element.elementapp.exception.authentication.AuthenticationException;
import by.element.elementapp.models.AuthenticationDto;
import by.element.elementapp.models.AuthenticationDataShortDto;

public interface AuthenticationService {
    AuthenticationDto isUserAuthenticated(AuthenticationDataShortDto authorizationData) throws AuthenticationException;
}

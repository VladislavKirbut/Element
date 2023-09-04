package by.element.elementapp.service.authentication;

import by.element.elementapp.exception.authentication.AuthenticationException;
import by.element.elementapp.models.security.AccessToken;
import by.element.elementapp.models.user.AuthenticationSignInDto;
import by.element.elementapp.models.user.AuthenticationSignUpDto;

public interface AuthenticationService {
    AccessToken signIn(AuthenticationSignInDto signInDto) throws AuthenticationException;
    AccessToken signUp(AuthenticationSignUpDto signUpDto);
}

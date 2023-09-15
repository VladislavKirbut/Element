package by.element.app.service.authentication;

import by.element.app.exception.authentication.AuthenticationException;
import by.element.app.models.security.AccessToken;
import by.element.app.models.user.AuthenticationSignInDto;
import by.element.app.models.user.AuthenticationSignUpDto;

public interface AuthenticationService {
    AccessToken signIn(AuthenticationSignInDto signInDto) throws AuthenticationException;
    AccessToken signUp(AuthenticationSignUpDto signUpDto);
}

package by.element.app.service.authentication;

import by.element.app.exception.authentication.AuthenticationException;
import by.element.app.models.security.AccessToken;
import by.element.app.models.user.dto.AuthenticationSignInDto;
import by.element.app.models.user.dto.AuthenticationSignUpDto;

public interface AuthenticationService {
    AccessToken signIn(AuthenticationSignInDto signInDto) throws AuthenticationException;
    AccessToken signUp(AuthenticationSignUpDto signUpDto);
}

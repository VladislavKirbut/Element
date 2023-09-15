package by.element.app.models.user;

import lombok.Value;

@Value
public class AuthenticationSignInDto {
    String phoneNumber;
    String password;
}

package by.element.elementapp.models.user;

import lombok.Value;

@Value
public class AuthenticationSignInDto {
    String phoneNumber;
    String password;
}

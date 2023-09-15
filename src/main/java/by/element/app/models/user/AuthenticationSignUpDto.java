package by.element.app.models.user;

import lombok.Value;

@Value
public class AuthenticationSignUpDto {
    String name;
    String surname;
    String email;
    String phoneNumber;
    String password;
}

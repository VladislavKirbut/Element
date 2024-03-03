package by.element.app.models.user;

import lombok.Value;

import java.util.UUID;

@Value
public class AuthenticationDto {
    UUID id;
    UserDto userDto;
    String phoneNumber;
}

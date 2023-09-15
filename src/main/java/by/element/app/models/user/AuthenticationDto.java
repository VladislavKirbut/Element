package by.element.app.models.user;

import lombok.Value;

import java.util.UUID;

@Value
public class AuthenticationDto {
    UUID id;
    UserDto userDto;
    String phoneNumber;

    public static AuthenticationDto from(AuthenticationData authenticationData) {
        return new AuthenticationDto(
                authenticationData.getUsersId(),
                UserDto.from(authenticationData.getUsers()),
                authenticationData.getPhoneNumber());
    }
}

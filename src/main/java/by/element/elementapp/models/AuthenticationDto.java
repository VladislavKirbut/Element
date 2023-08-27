package by.element.elementapp.models;

import lombok.Value;

import java.util.UUID;

@Value
public class AuthenticationDto {
    UUID id;
    Users users;
    String phoneNumber;
    String password;

    public static AuthenticationDto from(AuthenticationData authenticationData) {
        return new AuthenticationDto(
                authenticationData.getId(),
                authenticationData.getUsers(),
                authenticationData.getPhoneNumber(),
                authenticationData.getPassword());
    }
}

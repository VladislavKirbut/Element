package by.element.app.models.user;

import lombok.Value;

@Value
public class UserProfileDto {
    String name;
    String surname;
    String email;
    String phoneNumber;

    public static UserProfileDto from(Users user) {
        return new UserProfileDto(
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getAuthenticationData().getPhoneNumber()
        );
    }
}

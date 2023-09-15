package by.element.app.models.user;

import lombok.Value;

@Value
public class UserDto {
    String name;
    String surname;
    String email;
    public static UserDto from(Users user) {
        return new UserDto(
                user.getName(),
                user.getSurname(),
                user.getEmail()
        );
    }
}

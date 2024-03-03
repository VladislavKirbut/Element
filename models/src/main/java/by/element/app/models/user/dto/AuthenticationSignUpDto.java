package by.element.app.models.user.dto;

import by.element.app.validation.Email;
import by.element.app.validation.NumberValid;
import by.element.app.validation.PasswordValid;
import by.element.app.validation.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;
import org.springframework.validation.annotation.Validated;

@Value
@Validated
@UserInfo
@Schema(description = "Authentication sign up dto.")
public class AuthenticationSignUpDto {

    @NotBlank(message = "Name field cannot be empty.")
    @Schema(example = "Ivan", requiredMode = Schema.RequiredMode.REQUIRED, type = "String",
            description = "User first name.")
    String name;

    @NotBlank(message = "Surname field cannot be empty.")
    @Schema(example = "Ivanov", requiredMode = Schema.RequiredMode.REQUIRED, type = "String",
            description = "User last name.")
    String surname;

    @Email
    @Schema(example = "ivan256@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED, type = "String",
            description = "User email.")
    String email;

    @NumberValid
    @Schema(example = "+375332598654", requiredMode = Schema.RequiredMode.REQUIRED, type = "String",
            description = "Phone number.")
    String phoneNumber;

    @PasswordValid
    @Schema(example = "1234567Ww", requiredMode = Schema.RequiredMode.REQUIRED, type = "String",
            description = "Password.")
    String password;

}

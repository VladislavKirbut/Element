package by.element.app.models.user.dto;

import by.element.app.validation.NumberValid;
import by.element.app.validation.PasswordValid;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;
import org.springframework.validation.annotation.Validated;

@Value
@Validated
public class AuthenticationSignInDto {

    @NumberValid
    @Schema(example = "80295684512", requiredMode = Schema.RequiredMode.REQUIRED, type = "String",
            description = "User phone number.")
    String phoneNumber;

    @PasswordValid
    @Schema(example = "1234567Wv", requiredMode = Schema.RequiredMode.REQUIRED, type = "String",
            description = "Password.")
    String password;

}

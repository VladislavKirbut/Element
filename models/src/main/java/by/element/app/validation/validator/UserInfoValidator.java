package by.element.app.validation.validator;

import by.element.app.models.user.dto.AuthenticationSignUpDto;
import by.element.app.validation.UserInfo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UserInfoValidator implements ConstraintValidator<UserInfo, AuthenticationSignUpDto> {

    private final static String USER_INFO_PATTERN = "(\\w){1,15}";

    @Override
    public boolean isValid(AuthenticationSignUpDto dto, ConstraintValidatorContext context) {
        if (!dto.getName().matches(USER_INFO_PATTERN)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Name field is invalid.");
            return false;
        } else if (!dto.getSurname().matches(USER_INFO_PATTERN)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Surname field is invalid.");
            return false;
        }

        return true;
    }
}

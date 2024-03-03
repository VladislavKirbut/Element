package by.element.app.validation;

import by.element.app.validation.validator.EmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.*;

@Documented
@NotBlank(message = "Email field cannot be empty.")
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface Email {

    String message() default "{Invalid email.}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

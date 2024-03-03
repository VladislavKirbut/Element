package by.element.app.validation;

import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message = "Phone number field must not be empty.")
@Pattern(regexp = "(\\+?375|80)(29|33|44|25)\\d{7}", message = "Phone number field is invalid.")
@ReportAsSingleViolation
public @interface NumberValid {

    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

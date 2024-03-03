package by.element.app.handler;

import by.element.app.exception.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public void handleValidationException(ValidationException exception) {
        log.info("Exception: ", exception);
    }
}

package by.element.app.exception.authentication;

public class AuthenticationException extends RuntimeException {
    private final String message;

    public AuthenticationException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

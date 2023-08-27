package by.element.elementapp.exception.authentication;

public class AuthenticationException extends Exception {
    private final String message;

    public AuthenticationException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

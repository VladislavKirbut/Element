package by.element.app.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

public class AuthUtil {

    private static final Path PATH_TO_DOMAIN = Path.of("src/main/resources/data/domain_names.txt");
    private static final int PASSWORD_MIN_LENGTH = 10;
    private final static int SURNAME_MAX_LENGTH = 15;
    private final static int NAME_MAX_LENGTH = 15;
    public static boolean isEmailCorrect(String email) {
        if (email == null || email.isBlank() || email.indexOf('@') == -1)
            return false;

        try {
            String userDomain = email.substring(email.indexOf('@') + 1);
            List<String> domains = Files.readAllLines(PATH_TO_DOMAIN, StandardCharsets.UTF_8);

            for (String domain: domains) {
                if (domain.equals(userDomain)) {
                    Pattern pattern = Pattern.compile("\\w+@" + Pattern.quote(domain),
                            Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
                    return pattern.matcher(email).find();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public static boolean isPhoneNumberCorrect(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank())
            return false;

        return phoneNumber.matches("\\+375(33|29|44)\\d{7}");
    }

    public static boolean isPasswordCorrect(String password) {
        if (password == null || password.isBlank() || password.length() < PASSWORD_MIN_LENGTH)
            return false;

        return password.matches("\\w+");
    }

    public static boolean isUserNameAndSurnameCorrect(String name, String surname) {
        return name != null && surname != null && !name.isBlank() && !surname.isBlank() && name.length() <= NAME_MAX_LENGTH
            && surname.length() <= SURNAME_MAX_LENGTH;
    }
}

package by.element.elementapp.repository.authentication;

import by.element.elementapp.models.user.AuthenticationData;
import by.element.elementapp.models.user.Users;

import java.util.Optional;
import java.util.UUID;

public interface AuthenticationRepository {
    void saveUserToDB(Users user);
    void removeUserFromDB(UUID id);
    Optional<Users> getUserByPhoneNumber(String phoneNumber);

    void createUser(Users user);
}

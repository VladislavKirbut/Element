package by.element.elementapp.repository.authentication;

import by.element.elementapp.models.AuthenticationData;
import by.element.elementapp.models.Users;

import java.util.Optional;
import java.util.UUID;

public interface AuthenticationRepository {
    void saveUserToDB(Users user);
    void removeUserFromDB(UUID id);
    Optional<AuthenticationData> getAuthorizationDataByPhoneNumber(String phoneNumber);
}

package by.element.elementapp.repository.authorization;

import by.element.elementapp.models.Users;

import java.util.UUID;

public interface AuthorizationPageRepository {
    void saveUserToDB(Users user);
    void removeUserFromDB(UUID id);
}

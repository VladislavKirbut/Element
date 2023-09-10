package by.element.elementapp.repository.chat;

import by.element.elementapp.models.user.Users;

import java.util.Optional;
import java.util.UUID;

public interface ChatRepository {
    Optional<Users> getUserInfo(UUID userId);

}

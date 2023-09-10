package by.element.elementapp.service.chat;

import by.element.elementapp.models.security.AccountPrincipal;
import by.element.elementapp.models.user.UserProfileDto;
import by.element.elementapp.models.user.Users;

import java.util.UUID;

public interface ChatService {
    UserProfileDto getUserById(UUID userId);
}

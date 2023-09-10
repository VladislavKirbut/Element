package by.element.elementapp.service.chat;

import by.element.elementapp.exception.authentication.AuthenticationException;
import by.element.elementapp.models.user.UserProfileDto;
import by.element.elementapp.models.user.Users;
import by.element.elementapp.repository.chat.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserChatService implements ChatService {

    private final ChatRepository chatRepository;

    @Transactional
    @Override
    public UserProfileDto getUserById(UUID userId) {
        Users user = chatRepository.getUserInfo(
                userId
        ).orElseThrow(() -> new AuthenticationException("User doesn't exist"));

        return UserProfileDto.from(user);
    }
}

package by.element.app.service.chat;

import by.element.app.exception.authentication.AuthenticationException;
import by.element.app.models.folder.FolderDto;
import by.element.app.models.user.UserProfileDto;
import by.element.app.models.user.Users;
import by.element.app.repository.chat.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional
    @Override
    public List<FolderDto> getFolderByUserId(UUID userId) {
        return chatRepository.getFolderByUserId(userId)
                .stream()
                .map(FolderDto::from)
                .toList();
    }

    @Transactional
    @Override
    public void addFolder(UUID userId, FolderDto folderName) {
        chatRepository.createFolder(userId, folderName.getFolderName());
    }
}

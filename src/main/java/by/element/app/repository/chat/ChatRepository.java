package by.element.app.repository.chat;

import by.element.app.models.folder.Folder;
import by.element.app.models.user.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRepository {
    Optional<Users> getUserInfo(UUID userId);

    List<Folder> getFolderByUserId(UUID userId);
    void createFolder(UUID userId, String folderName);
}

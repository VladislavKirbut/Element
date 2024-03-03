package by.element.app.service.chat;

import by.element.app.models.folder.FolderDto;
import by.element.app.models.user.UserProfileDto;

import java.util.List;
import java.util.UUID;

public interface ChatService {
    UserProfileDto getUserById(UUID userId);

    List<FolderDto> getFolderByUserId(UUID userId);
    void addFolder(UUID userId, FolderDto folderName);
}

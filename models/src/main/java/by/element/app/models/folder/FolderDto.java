package by.element.app.models.folder;

import lombok.Value;

@Value
public class FolderDto {

    String folderName;
    public static FolderDto from(Folder folder) {
        return new FolderDto(
                folder.getFolderName()
        );
    }
}

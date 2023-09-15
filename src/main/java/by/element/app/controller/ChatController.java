package by.element.app.controller;

import by.element.app.models.folder.FolderDto;
import by.element.app.models.user.UserProfileDto;
import by.element.app.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/element")
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chatPage")
    public ModelAndView getChatPage(@RequestParam UUID userId) {
        UserProfileDto userProfile = chatService.getUserById(userId);
        List<FolderDto> folders = chatService.getFolderByUserId(userId);
        return new ModelAndView(
                "chatPage",
                Map.of("userProfile", userProfile,
                        "folders", folders,
                        "userId", userId)
        );
    }
    @PostMapping("/chatPage/add-folder")
    public ResponseEntity<String> addFolder(@RequestParam UUID userId, @RequestParam FolderDto folderName, UriComponentsBuilder urlBuilder) {
        chatService.addFolder(userId, folderName);

        URI uri = urlBuilder
                .path("/element/chatPage")
                .queryParam("userId", userId)
                .build()
                .toUri();

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(uri)
                .build();
    }
}

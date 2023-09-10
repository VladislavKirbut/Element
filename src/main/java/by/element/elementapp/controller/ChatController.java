package by.element.elementapp.controller;

import by.element.elementapp.models.security.AccountPrincipal;
import by.element.elementapp.models.security.UserPrincipal;
import by.element.elementapp.models.user.UserProfileDto;
import by.element.elementapp.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
        return new ModelAndView(
                "chatPage",
                Map.of("userProfile", userProfile)
        );
    }
}

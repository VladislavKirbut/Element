package by.element.elementapp.service.chat;

import by.element.elementapp.models.security.AccountPrincipal;
import by.element.elementapp.models.user.Users;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserChatService implements ChatService {

    @Transactional
    @Override
    public Users getUserById(AccountPrincipal accountPrincipal) {
        return null;
    }

}

package by.element.elementapp.service.chat;

import by.element.elementapp.models.security.AccountPrincipal;
import by.element.elementapp.models.user.Users;

public interface ChatService {
    public Users getUserById(AccountPrincipal accountPrincipal);
}

package by.element.elementapp.service.authorization;


import by.element.elementapp.repository.authorization.AuthorizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthorizationService implements AuthorizationService {

    private final AuthorizationRepository authorizationRepository;
}

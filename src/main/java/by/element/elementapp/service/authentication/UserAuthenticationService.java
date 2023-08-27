package by.element.elementapp.service.authentication;


import by.element.elementapp.exception.authentication.AuthenticationException;
import by.element.elementapp.models.AuthenticationData;
import by.element.elementapp.models.AuthenticationDto;
import by.element.elementapp.models.AuthenticationDataShortDto;
import by.element.elementapp.repository.authentication.UserAuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService implements AuthenticationService {
    private final UserAuthenticationRepository authenticationRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public AuthenticationDto isUserAuthenticated(AuthenticationDataShortDto authorizationData) throws AuthenticationException {
        if (authenticationRepository.getAuthorizationDataByPhoneNumber(authorizationData.getPhoneNumber()).isEmpty())
            throw new AuthenticationException("User doesn't exist");

        AuthenticationData userData = authenticationRepository.getAuthorizationDataByPhoneNumber(
                authorizationData.getPhoneNumber()
                ).get();

        boolean isPasswordCorrect =  passwordEncoder.matches(
                authorizationData.getPassword(),
                userData.getPassword()
        );

        if (!isPasswordCorrect) throw new AuthenticationException("Incorrect password");

        return AuthenticationDto.from(userData);
    }
}

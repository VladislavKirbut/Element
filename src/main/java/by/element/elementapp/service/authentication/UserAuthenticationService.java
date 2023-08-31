package by.element.elementapp.service.authentication;


import by.element.elementapp.exception.authentication.AuthenticationException;
import by.element.elementapp.models.user.AuthenticationData;
import by.element.elementapp.models.user.Users;
import by.element.elementapp.models.user.AuthenticationDto;
import by.element.elementapp.models.user.AuthenticationSignInDto;
import by.element.elementapp.models.user.AuthenticationSignUpDto;
import by.element.elementapp.models.user.UserDto;
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
    public AuthenticationDto isUserAuthenticated(AuthenticationSignInDto authorizationData) throws AuthenticationException {
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

    public UserDto signUp(AuthenticationSignUpDto authenticationSignUpDto) {
        String hashedPassword = passwordEncoder.encode(authenticationSignUpDto.getPassword());

        Users user = create(authenticationSignUpDto, hashedPassword);
        return UserDto.from(user);
    }

    private Users create(AuthenticationSignUpDto authenticationSignUpDto, String hashedPassword) {

        Users user = new Users()
                .setAuthenticationData(
                        new AuthenticationData()
                                .setPhoneNumber(authenticationSignUpDto.getPhoneNumber())
                                .setPassword(hashedPassword)
                )
                .setName(authenticationSignUpDto.getName())
                .setSurname(authenticationSignUpDto.getSurname())
                .setEmail(authenticationSignUpDto.getEmail());

        authenticationRepository.createUser(user);

        return user;
    }

}

package by.element.elementapp.service.authentication;


import by.element.elementapp.exception.authentication.AuthenticationException;
import by.element.elementapp.models.security.AccessToken;
import by.element.elementapp.models.security.UserPrincipal;
import by.element.elementapp.models.user.AuthenticationData;
import by.element.elementapp.models.user.Users;
import by.element.elementapp.models.user.AuthenticationSignInDto;
import by.element.elementapp.models.user.AuthenticationSignUpDto;
import by.element.elementapp.repository.authentication.AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionOperations;
@Service
@RequiredArgsConstructor
public class UserAuthenticationService implements AuthenticationService {
    private final AuthenticationRepository authenticationRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenService accessTokenService;
    private final TransactionOperations txOps;

    @Transactional
    @Override
    public AccessToken signIn(AuthenticationSignInDto signInDto) throws AuthenticationException {
        Users userData = authenticationRepository.getUserByPhoneNumber(
                signInDto.getPhoneNumber()
        ).orElseThrow(() -> new AuthenticationException("User doesn't exist"));

        boolean isPasswordCorrect = passwordEncoder.matches(
                signInDto.getPassword(),
                userData.getAuthenticationData().getPassword()
        );

        if (!isPasswordCorrect) throw new AuthenticationException("Incorrect login or password.");

        UserPrincipal userPrincipal = UserPrincipal.from(userData);

        return accessTokenService.generateToken(userPrincipal);
    }

    @Transactional
    @Override
    public AccessToken signUp(AuthenticationSignUpDto signUpDto) {
        String hashedPassword = passwordEncoder.encode(signUpDto.getPassword());

        Users user = create(signUpDto, hashedPassword);

        UserPrincipal userPrincipal = UserPrincipal.from(user);
        return accessTokenService.generateToken(userPrincipal);
    }

    private Users create(AuthenticationSignUpDto signUpDto, String hashedPassword) {

        return txOps.execute(tx -> {
            boolean isUserExist = authenticationRepository.getUserByPhoneNumber(
                    signUpDto.getPhoneNumber()
            ).isPresent();

            if (isUserExist)
                throw new AuthenticationException("User already exist.");

            Users user = new Users()
                    .setAuthenticationData(
                            new AuthenticationData()
                                    .setPhoneNumber(signUpDto.getPhoneNumber())
                                    .setPassword(hashedPassword)
                    )
                    .setName(signUpDto.getName())
                    .setSurname(signUpDto.getSurname())
                    .setEmail(signUpDto.getEmail());

            authenticationRepository.createUser(user);

            return user;
        });
    }
}

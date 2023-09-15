package by.element.app.service.authentication;


import by.element.app.exception.authentication.AuthenticationException;
import by.element.app.models.security.AccessToken;
import by.element.app.models.security.UserPrincipal;
import by.element.app.models.user.AuthenticationData;
import by.element.app.models.user.Users;
import by.element.app.models.user.AuthenticationSignInDto;
import by.element.app.models.user.AuthenticationSignUpDto;
import by.element.app.repository.authentication.AuthenticationRepository;
import by.element.app.util.AuthUtil;
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

        if (!AuthUtil.isPhoneNumberCorrect(signInDto.getPhoneNumber()))
            throw new AuthenticationException("Incorrect number");
        if (!AuthUtil.isPasswordCorrect(signInDto.getPassword()))
            throw new AuthenticationException("Password should be a-zA-Z0-9");

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

        if (!AuthUtil.isUserNameAndSurnameCorrect(signUpDto.getName(), signUpDto.getSurname()))
            throw new AuthenticationException("Incorrect name or surname");
        if (!AuthUtil.isEmailCorrect(signUpDto.getEmail()))
            throw new AuthenticationException("Incorrect email");
        if (!AuthUtil.isPhoneNumberCorrect(signUpDto.getPhoneNumber()))
            throw new AuthenticationException("Incorrect number");
        if (!AuthUtil.isPasswordCorrect(signUpDto.getPassword()))
            throw new AuthenticationException("Password should be a-zA-Z0-9");

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

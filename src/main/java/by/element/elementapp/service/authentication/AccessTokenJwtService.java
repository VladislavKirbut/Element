package by.element.elementapp.service.authentication;

import by.element.elementapp.models.security.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccessTokenJwtService implements AccessTokenService {
    private final AccessTokenProperties tokenProperties;
    private final Algorithm jwtAlgorithm;

    private final JWTVerifier jwtVerifier;

    @Override
    public AccessToken generateToken(AccountPrincipal accountPrincipal) {
        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plus(tokenProperties.getTimeToLive());
        String token = JWT.create()
                .withJWTId(UUID.randomUUID().toString())
                .withSubject(accountPrincipal.getId().toString())
                .withClaim("role", accountPrincipal.getRole().toString())
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .sign(jwtAlgorithm);
        return new AccessToken(token, accountPrincipal, issuedAt, expiresAt);
    }

    @Override
    public AccountPrincipal authenticate(String tokenValue) {
        try {
            DecodedJWT token = jwtVerifier.verify(tokenValue);
            UUID accountId = token.getClaim("sub").as(UUID.class);
            AccountRole accountRole = token.getClaim("role").as(AccountRole.class);
            return switch(accountRole) {
                case USER -> new UserPrincipal(accountId);
            };
        } catch(TokenExpiredException exception) {
            throw new CredentialsExpiredException("JWT token is expired", exception);
        } catch(JWTVerificationException exception) {
            throw new BadCredentialsException("JWT is invalid", exception);
        }
    }
}

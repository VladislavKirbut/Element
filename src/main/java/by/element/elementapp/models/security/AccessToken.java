package by.element.elementapp.models.security;

import lombok.Value;

import java.time.Instant;

@Value
public class AccessToken {
    String value; // строка (header, payload, signature)
    AccountPrincipal principal;
    Instant issuedAt;
    Instant expiresAt;
}

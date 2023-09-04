package by.element.elementapp.config;

import by.element.elementapp.models.security.AccessTokenProperties;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(config -> config
                        .requestMatchers(HttpMethod.GET, "/element/startPage").permitAll()
                        .requestMatchers(HttpMethod.GET, "/element/signIn").permitAll()
                        .requestMatchers(HttpMethod.GET, "/element/signUp").permitAll()
                        .requestMatchers("/**").authenticated()
                )
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public Algorithm jwtAlgorithm(AccessTokenProperties tokenProperties) {
        return Algorithm.HMAC256(tokenProperties.getSecret());
    }
    @Bean
    public JWTVerifier jwtVerifier(Algorithm jwtAlgorithm) {
        return JWT.require(jwtAlgorithm).build();
    }
}

package by.element.elementapp.config;

import by.element.elementapp.models.security.AccessTokenProperties;
import by.element.elementapp.models.security.AccountRole;
import by.element.elementapp.service.authentication.AccessTokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.boot.autoconfigure.security.StaticResourceLocation;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AccessTokenService accessTokenService) throws Exception {
        return httpSecurity
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable) // отключаем защиту от межсайтовой подделки запросов
/*                .formLogin(config -> config
                        .loginPage("/element/signUp")
                )*/
                .authorizeHttpRequests(config -> config
                        .requestMatchers(HttpMethod.GET, "/element/startPage").permitAll()
                        .requestMatchers(HttpMethod.POST, "/element/signIn").permitAll()
                        .requestMatchers(HttpMethod.GET, "/element/signIn").permitAll()
                        .requestMatchers(HttpMethod.GET,"/element/signUp").permitAll()
                        .requestMatchers(HttpMethod.POST,"/element/signUp").permitAll()
                        .requestMatchers(PathRequest.toStaticResources().at(
                                StaticResourceLocation.IMAGES,
                                StaticResourceLocation.CSS,
                                StaticResourceLocation.JAVA_SCRIPT
                        )).permitAll()
                        .requestMatchers("/**").permitAll()
                )
                .addFilterAfter(new AccessTokenAuthenticationFilter(accessTokenService), BasicAuthenticationFilter.class)
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

package by.element.app.models.security;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Value
@ConfigurationProperties(prefix = "element.access-token")
public class AccessTokenProperties {
    String secret;
    Duration timeToLive;
}

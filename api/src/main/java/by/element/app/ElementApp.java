package by.element.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ElementApp {
    public static void main(String[] args) {
        SpringApplication.run(ElementApp.class, args);
    }
}

package by.element.elementapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ElementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElementApplication.class, args);
    }
}

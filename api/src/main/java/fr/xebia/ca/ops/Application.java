package fr.xebia.ca.ops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("fr.xebia.ca.ops")
public class Application {

    public Application() {}

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

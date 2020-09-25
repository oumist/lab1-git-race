package es.unizar.webeng.hello;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        // Sets the WebApplicationType to REACTIVE to enable reactive functionalities to Thymeleaf
        new SpringApplicationBuilder(Application.class).web(WebApplicationType.REACTIVE).run(args);
    }
}

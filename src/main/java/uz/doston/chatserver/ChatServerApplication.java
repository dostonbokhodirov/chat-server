package uz.doston.chatserver;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class ChatServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatServerApplication.class, args);
    }

}

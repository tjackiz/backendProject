package de.tjackiz.gatewayService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner demo() {
        return (argss) -> {
            System.out.println("\t### random uuid: " + UUID.randomUUID().toString());
        };
    }
}

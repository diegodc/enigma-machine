package rest;

import enigma.service.EnigmaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestService {

    public static void main(String[] args) {
        SpringApplication.run(RestService.class, args);
    }

    @Bean
    public EnigmaService getService() {
        return new EnigmaService();
    }

}

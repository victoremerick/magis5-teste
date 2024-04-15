package br.victoremerick.magis5teste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class Magis5TesteApplication {

    public static void main(String[] args) {
        SpringApplication.run(Magis5TesteApplication.class, args);
    }

}

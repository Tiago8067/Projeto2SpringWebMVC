package com.example.projeto2springwebmvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Projeto2SpringWebMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(Projeto2SpringWebMvcApplication.class, args);
    }

    // criar metodo de linha de comando para testar a app e retorna uma funcao que reinicie
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {

        };
    }

}

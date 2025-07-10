package com.pdrake.cocktailapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.pdrake.cocktailapp.repositories")
public class CocktailAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CocktailAppApplication.class, args);
    }

}

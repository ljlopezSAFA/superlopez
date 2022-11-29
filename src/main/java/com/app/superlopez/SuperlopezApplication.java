package com.app.superlopez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.app.superlopez.repository")
@EnableJpaRepositories(basePackages = "com.app.superlopez.repository")
public class SuperlopezApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperlopezApplication.class, args);
    }

}

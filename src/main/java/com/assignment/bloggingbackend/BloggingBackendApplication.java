package com.assignment.bloggingbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BloggingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloggingBackendApplication.class, args);
    }

}

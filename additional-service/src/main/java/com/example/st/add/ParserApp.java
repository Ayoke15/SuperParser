package com.example.st.add;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"org.example.st.model"})
@ComponentScan(basePackages = "org.example.st.client")
public class ParserApp {
    public static void main(String[] args) {
        SpringApplication.run(ParserApp.class, args);
    }
}

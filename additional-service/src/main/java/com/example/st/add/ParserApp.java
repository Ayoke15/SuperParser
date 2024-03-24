package com.example.st.add;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"org.example.st.client", "org.example.st.model"})
public class ParserApp {
    public static void main(String[] args) {
        SpringApplication.run(ParserApp.class, args);
    }
}

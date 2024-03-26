package com.example.st.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "org.example.st.model")
public class MainServiceApp {
        public static void main(String[] args) {
                SpringApplication.run(MainServiceApp.class, args);
        }
}

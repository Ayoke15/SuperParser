package com.example.st.add.controller;

import com.example.st.add.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ParserController {
    private final ParserService parserService;

    @Autowired
    public ParserController(ParserService parserService) {
        this.parserService = parserService;
    }

    @GetMapping("/test")
    public ResponseEntity<Void> parsePage() {
        return parserService.parsePage();
    }
}

package com.example.st.add.controller;

import com.example.st.add.service.ParserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class ParserController {
    private final ParserService parserService;

    @GetMapping("/parse-websites")
    public ResponseEntity<Void> parseAllWebsites(){
        return parserService.parseAllWebsites();
    }
}

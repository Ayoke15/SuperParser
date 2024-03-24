package com.example.st.add.controller;

import com.example.st.add.model.Tender;
import com.example.st.add.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
public class ParserController {
    private final ParserService parserService;

    @Autowired
    public ParserController(ParserService parserService) {
        this.parserService = parserService;
    }

//    @GetMapping("/test")
//    public List<Tender> parsePage() {
//        return parserService.parsePage();
//    }
}

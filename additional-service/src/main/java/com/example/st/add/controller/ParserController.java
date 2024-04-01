package com.example.st.add.controller;

import com.example.st.add.service.ParserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для выполнения парсинга веб-сайтов.
 */
@RestController
@AllArgsConstructor
public class ParserController {
    private final ParserService parserService;

    /**
     * Метод для выполнения парсинга всех веб-сайтов в указанное время (1:00 AM).
     *
     * @return Ответ о выполнении операции парсинга.
     */
    //TODO CHECK WARNING
    @Scheduled(cron = "0 0 1 * * ?")
    @GetMapping("/parse-websites")
    public ResponseEntity<Void> parseAllWebsites(){
        return parserService.parseAllWebsites();
    }
}

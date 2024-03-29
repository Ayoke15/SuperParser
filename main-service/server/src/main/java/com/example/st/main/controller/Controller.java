package com.example.st.main.controller;

import com.example.st.main.repository.TenderJpaRepository;
import org.example.st.dto.TenderDto;
import com.example.st.main.service.TenderService;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.st.dto.NewTenderDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс-контроллер для обработки HTTP-запросов
 */
@RestController
@Validated
@Slf4j
@RequestMapping(path = "/api")
@AllArgsConstructor
public class Controller {

    private TenderService tenderService;
    private TenderJpaRepository repository;

    /**
     * Обрабатывает GET-запрос для получения списка всех тендеров.
     *
     * @param from индекс начальной записи
     * @param size размер страницы
     * @return список всех тендеров, ответ об успешном получении данных
     */
    @GetMapping("/get-all-tenders")
    ResponseEntity<List<TenderDto>> getAllTenders(
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(required = false, defaultValue = "20") @Positive Integer size
    ) {
        log.info("get-all-tenders request accepted from={}, size={}", from, size);
        PageRequest page = PageRequest.of(from / size, size);
        List<TenderDto> tenderDtos = tenderService.getAllTenders(page);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(
                HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,
                "TotalPagesCount"
        );
        responseHeaders.set(
                "TotalPagesCount",
                String.valueOf(Math.ceil((double) repository.findAll().size() / (double) size))
        );
        return ResponseEntity.ok().headers(responseHeaders).body(tenderDtos);
    }

    /**
     * Обрабатывает POST-запрос для создания нового тендера.
     *
     * @param newTenderDto данные нового тендера
     * @return ответ об успешном создании тендера
     */
    @PostMapping("/create-tender")
    ResponseEntity<Void> postTender(
            @RequestBody @Validated org.example.st.dto.NewTenderDto newTenderDto
    ) {
        log.info("create-tender request accepted, newTenderDto{}", newTenderDto);
        tenderService.postTender(newTenderDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Обрабатывает POST-запрос для создания нового тендера.
     *
     * @param newTenderDtos список объектов newTenderDtos, хранящих данные новых тендеров
     * @return ответ об успешном создании тендеров
     */
    @PostMapping("/create-tenders")
    ResponseEntity<Void> postTenderList(
            @RequestBody @Validated List<NewTenderDto> newTenderDtos
    ) {
        log.info("create-tenders request accepted, newTenderDtos size {}", newTenderDtos.size());
        tenderService.postTenderList(newTenderDtos);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

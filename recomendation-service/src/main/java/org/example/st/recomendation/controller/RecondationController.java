package org.example.st.recomendation.controller;

import org.example.st.recomendation.model.EventDTO;
import org.example.st.recomendation.model.FilterDTO;
import org.example.st.recomendation.model.ShortTenderDTO;
import org.example.st.recomendation.service.RecomendationService;
import org.example.st.recomendation.service.TenderRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RecondationController {
    @Autowired
    private RecomendationService filterService; // Сервис для обработки логики

    private final TenderRankingService tenderRankingService;

    @Autowired
    public RecondationController(TenderRankingService tenderRankingService) {
        this.tenderRankingService = tenderRankingService;
    }
    private FilterDTO currentFilter;
    private final List<ShortTenderDTO> tenders = new ArrayList<>();
    private Map<String, Object> weights;

    @PostMapping("/update")
    public ResponseEntity<?> updateFilters(@RequestBody List<EventDTO> events) {
        try {
            filterService.updateFilterData(events);
            return ResponseEntity.ok(Map.of("status", "success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("status", "error", "message", e.getMessage()));
        }
    }

    @GetMapping("/weights")
    public ResponseEntity<?> getWeights() {
        try {
            Map<String, Object> weights = filterService.calculateWeights();
            return ResponseEntity.ok(weights);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("status", "error", "message", e.getMessage()));
        }
    }

    @PostMapping("/filters")
    public ResponseEntity<?> submitFilters(@RequestBody FilterDTO filter) {
        this.currentFilter = filter; // Сохраняем фильтр в переменную
        return ResponseEntity.ok().build(); // Отправляем подтверждение об успешном сохранении
    }
    @PostMapping("/submit")
    public ResponseEntity<List<Long>> submitTenders(@RequestBody List<ShortTenderDTO> tenderList) {
        this.tenders.clear();
        this.tenders.addAll(tenderList);
        this.weights = filterService.calculateWeights();
        List<Long> rankedTenderIds = tenderRankingService.rankTenders(this.tenders, this.currentFilter, this.weights);

        return ResponseEntity.ok(rankedTenderIds);
    }

}


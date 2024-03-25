package org.example.st.recomendation.controller;

import org.example.st.recomendation.model.EventDTO;
import org.example.st.recomendation.service.RecomendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RecondationController {
    @Autowired
    private RecomendationService filterService; // Сервис для обработки логики

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

}


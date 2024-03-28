package org.example.st.recomendation.service;

import org.example.st.recomendation.model.FilterDTO;
import org.example.st.recomendation.model.ShortTenderDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TenderRankingService {

    public List<Long> rankTenders(List<ShortTenderDTO> tenders, FilterDTO currentFilter, Map<String, Object> weights) {
        Map<Long, Double> tenderWeights = new HashMap<>();

        for (ShortTenderDTO tender : tenders) {
            if (!tender.getProduct().equals(currentFilter.getProduct())) {
                continue; // Если продукт не соответствует фильтру, пропускаем тендер
            }

            double weight = 0.0;
            // Приводим веса к Double перед использованием
            weight += Objects.equals(tender.getStartDate(), currentFilter.getStartDate()) ? getWeightAsDouble(weights, "startDate") : 0;
            weight += Objects.equals(tender.getEndDate(), currentFilter.getEndDate()) ? getWeightAsDouble(weights, "endDate") : 0;
            weight += tender.getRegion().equals(currentFilter.getRegion()) ? getWeightAsDouble(weights, "region") : 0;

            tenderWeights.put(tender.getId(), weight);
        }

        return tenderWeights.entrySet().stream()
                .sorted(Map.Entry.<Long, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private double getWeightAsDouble(Map<String, Object> weights, String key) {
        Object value = weights.get(key);
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        return 0.0; // Возвращаем 0.0, если значение не может быть приведено к Double
    }
}
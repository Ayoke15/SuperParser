package org.example.st.recomendation.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.example.st.model.Tender;
import org.example.st.recomendation.model.EventDTO;
import org.example.st.recomendation.model.FilterData;
import org.example.st.recomendation.unit.JsonFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecomendationService {

    private final JsonFileUtil jsonFileUtil;
    private final Path pathToFile;
    private Map<String, FilterData> filterDataMap;

    @Autowired
    public RecomendationService(JsonFileUtil jsonFileUtil) {
        this.jsonFileUtil = jsonFileUtil;
        // Путь к файлу filter_data.json на уровне pom.xml, изменил имя на filtered_data.json
        this.pathToFile = Paths.get("filtered_data.json").toAbsolutePath();
    }

    @PostConstruct
    public void init() {
        File file = pathToFile.toFile();
        if (file.exists() && file.canRead()) {
            try {
                filterDataMap = jsonFileUtil.readFromFile(file.getPath(), new TypeReference<HashMap<String, FilterData>>() {});
            } catch (IOException e) {
                e.printStackTrace();
                filterDataMap = new HashMap<>(); // Инициализируем пустой картой, если не удалось прочитать файл
            }
        } else {
            filterDataMap = new HashMap<>(); // Инициализируем пустой картой, если файл не существует
        }
    }

    public void updateFilterData(List<EventDTO> events) {
        for (EventDTO event : events) {
            FilterData data = filterDataMap.getOrDefault(event.getFilter(), new FilterData());

            data.setTotalCount(data.getTotalCount() + 1);
            if (event.getSuccess() != null && event.getSuccess()) {
                data.setSuccessCount(data.getSuccessCount() + 1);
            }
            data.setAlpha(data.getSuccessCount() + 1);
            data.setBeta(data.getTotalCount() - data.getSuccessCount() + 1);

            filterDataMap.put(event.getFilter(), data);
        }
        saveFilterData();
    }

    private void saveFilterData() {
        try {
            jsonFileUtil.saveToFile(pathToFile.toString(), filterDataMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> calculateWeights() {
        return calculateWeightsRecursive(filterDataMap);
    }

    private Map<String, Object> calculateWeightsRecursive(Map<String, FilterData> filterData) {
        Map<String, Object> weights = new HashMap<>();
        for (Map.Entry<String, FilterData> entry : filterData.entrySet()) {
            String filterName = entry.getKey();
            FilterData data = entry.getValue();
            if (data.getAlpha() > 0 && data.getBeta() > 0) {
                double estimatedProb = (double) data.getAlpha() / (data.getAlpha() + data.getBeta());
                weights.put(filterName, estimatedProb);
            } else if (data.getServices() != null && !data.getServices().isEmpty()) {
                weights.put(filterName, calculateWeightsRecursive(data.getServices()));
            }
        }
        return weights;
    }

}

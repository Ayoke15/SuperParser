package org.example.st.recomendation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class FilterData {
    @JsonProperty("success_count")
    private int successCount;

    @JsonProperty("total_count")
    private int totalCount;

    private int alpha;
    private int beta;

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getBeta() {
        return beta;
    }

    public void setBeta(int beta) {
        this.beta = beta;
    }

    // Добавляем новое поле для вложенных данных
    private Map<String, FilterData> services;

    // Геттеры и сеттеры для вложенных данных
    public Map<String, FilterData> getServices() {
        return services;
    }

    public void setServices(Map<String, FilterData> services) {
        this.services = services;
    }
}

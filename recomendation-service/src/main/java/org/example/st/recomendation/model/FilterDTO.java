package org.example.st.recomendation.model;

import java.time.LocalDate;

public class FilterDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private String region;
    private String product;

    // Геттеры
    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getRegion() {
        return region;
    }

    public String getProduct() {
        return product;
    }

    // Сеттеры
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}

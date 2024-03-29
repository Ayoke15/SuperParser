package org.example.st.recomendation.model;

import java.time.LocalDate;
import java.util.List;


public class ShortTenderDTO {
        private Long id;
        private LocalDate startDate;
        private LocalDate endDate;
        private String region;
        private String name;
        private List<String> products;

    // Геттеры
    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getRegion() {
        return region;
    }

    public String getName() {
        return name;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }


    // Сеттеры
    public void setId(Long id) {
        this.id = id;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setName(String name) {
        this.name = name;
    }

}
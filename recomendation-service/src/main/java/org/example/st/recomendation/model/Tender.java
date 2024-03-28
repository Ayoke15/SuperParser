package org.example.st.recomendation.model;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
public class Tender {
    @Id
    private Long id;
    private String product;
    private Integer quantity;
    private LocalDate startDate;
    private LocalDate endDate;
    private String region;
    private String name;
    // Геттеры и сеттеры
}

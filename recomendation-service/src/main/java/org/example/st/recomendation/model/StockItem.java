package org.example.st.recomendation.model;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

@Entity
public class StockItem {
    @Id
    private Long id;
    private String product;
    private Integer quantity;
    // Геттеры и сеттеры
}

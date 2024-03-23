package com.example.st.main.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Класс, представляющий сущность тендера.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tender")
public class Tender {
    /**
     * Уникальный идентификатор тендера.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Валюта тендера.
     */
    @Enumerated(EnumType.STRING)
    private Currency currency;

    /**
     * Статус тендера.
     */
    //    @JoinColumn(name = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Status status;

    /**
     * Название тендера.
     */
    @Column
    private String name;

    /**
     * Дата начала тендера.
     */
    @Column
    private LocalDateTime startDate;

    /**
     * Дата окончания тендера.
     */
    @Column
    private LocalDateTime endDate;

    /**
     * Дата публикации тендера.
     */
    @Column
    private LocalDateTime publishDate;

    /**
     * Название компании, проводящей тендер.
     */
    @Column
    private String company;

    /**
     * Ссылка на тендер.
     */
    @Column
    private String link;

    /**
     * Начальная цена тендера.
     */
    @Column
    private String startPrice;

    /**
     * Код тендера.
     */
    @Column
    private String code;
}

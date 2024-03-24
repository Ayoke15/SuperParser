package org.example.st.model;

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
@ToString
public class Tender {
    /**
     * Уникальный идентификатор тендера, содержит код тендера.
     */
    @Id
    @Column(name = "code")
    private String code;

    /**
     * Валюта тендера.
     */
    @Enumerated(EnumType.STRING)
    private Currency currency;

    /**
     * Статус тендера.
     */
    @JoinColumn(name = "status_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Status status;

    /**
     * Название тендера.
     */
    @Column(name = "name")
    private String name;

    /**
     * Дата начала тендера.
     */
    @Column(name = "start_date")
    private LocalDateTime startDate;

    /**
     * Дата окончания тендера.
     */
    @Column(name = "end_date")
    private LocalDateTime endDate;

    /**
     * Дата публикации тендера.
     */
    @Column(name = "publish_date")
    private LocalDateTime publishDate;

    /**
     * Название компании, проводящей тендер.
     */
    @Column(name = "company")
    private String company;

    /**
     * Ссылка на тендер.
     */
    @Column(name = "link")
    private String link;

    /**
     * Начальная цена тендера.
     */
    @Column(name = "start_price")
    private String startPrice;
}

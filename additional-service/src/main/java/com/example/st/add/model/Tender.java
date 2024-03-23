package com.example.st.add.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Status status;

    /**
     * Название тендера.
     */
    @Column
    @NotNull
    private String name;

    /**
     * Дата начала тендера.
     */
    @Column
    @NotNull
    private LocalDateTime start_date;

    /**
     * Дата окончания тендера.
     */
    @Column
    @NotNull
    private LocalDateTime end_date;

    /**
     * Дата публикации тендера.
     */
    @Column
    @NotNull
    private LocalDateTime publish_date;

    /**
     * Название компании, проводящей тендер.
     */
    @Column
    @NotNull
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
    private String start_price;

    /**
     * Код тендера.
     */
    @Column
    private String code;

    @Override
    public String toString() {
        return "Tender{" +
                "id=" + id +
                ", currency=" + currency +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", publish_date=" + publish_date +
                ", company='" + company + '\'' +
                ", link='" + link + '\'' +
                ", start_price='" + start_price + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
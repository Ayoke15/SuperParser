package com.example.st.main.dto;

import com.example.st.main.model.Currency;
import com.example.st.main.model.Status;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Класс, представляющий объект передачи данных (DTO) для отображения тендера.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenderDto {

    /**
     * Формат даты и времени для отображения.
     */
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Уникальный код тендера, по которому тендер можно индентифицировать.
     */
    private String code;

    /**
     * Валюта тендера.
     */
    private Currency currency;

    /**
     * Статус тендера.
     */
    private Status status;

    /**
     * Название тендера.
     */
    private String name;

    /**
     * Дата и время начала тендера (в формате строки).
     */
    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    private String startDate;

    /**
     * Дата и время окончания тендера (в формате строки).
     */
    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    private String endDate;

    /**
     * Дата и время публикации тендера (в формате строки).
     */
    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    private String publishDate;

    /**
     * Название компании, проводящей тендер.
     */
    private String company;

    /**
     * Ссылка на тендер.
     */
    private String link;

    /**
     * Начальная цена тендера.
     */
    private String startPrice;
}

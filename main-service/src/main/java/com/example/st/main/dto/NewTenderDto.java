package com.example.st.main.dto;

import com.example.st.main.model.Currency;
import com.example.st.main.model.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Класс, представляющий объект передачи данных (DTO) для сохранения тендера.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewTenderDto {

    /**
     * Формат даты и времени для отображения.
     */
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Валюта тендера.
     */
    @NotNull(message = "currency may not be null")
    private Currency currency;

    /**
     * Статус тендера.
     */
    @NotNull(message = "status may not be null")
    private Status status;

    /**
     * Название тендера.
     */
    @NotNull(message = "name may not be null")
    @Size(max = 2000, min = 20, message = "name size should be between 20 and 2000 symbols")
    private String name;

    /**
     * Дата и время начала тендера (в формате строки).
     */
    @NotNull(message = "startDate may not be null or have non-String format")
    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    private String startDate;

    /**
     * Дата и время окончания тендера (в формате строки).
     */
    @NotNull(message = "endDate may not be null or have non-String format")
    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    private String endDate;

    /**
     * Дата и время публикации тендера (в формате строки).
     */
    @NotNull(message = "publishDate may not be null or have non-String format")
    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    private String publishDate;

    /**
     * Название компании, проводящей тендер.
     */
    @NotNull(message = "company name may not be null")
    @Size(max = 2000, min = 5, message = "company name size should be between 5 and 2000 symbols")
    private String company;

    /**
     * Ссылка на тендер.
     */
    private String link;

    /**
     * Начальная цена тендера.
     */
    private String startPrice;

    /**
     * Код тендера.
     */
    private String code;
}



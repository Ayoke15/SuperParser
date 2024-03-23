package com.example.st.main.mapper;

import com.example.st.main.dto.TenderDto;
import com.example.st.main.model.Tender;

import java.time.format.DateTimeFormatter;

/**
 * Класс, отвечающий за преобразование между объектами Tender и TenderDto.
 */
public class TenderMapper {

    /**
     * Формат даты и времени для отображения.
     */
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Преобразует объект Tender в объект TenderDto.
     *
     * @param tender объект Tender
     * @return объект TenderDto
     */
    public static TenderDto toTenderDto(Tender tender) {
        return TenderDto.builder()
            .currency(tender.getCurrency())
            .status(tender.getStatus())
            .name(tender.getName())
            .startDate(tender.getStart_date() != null ? tender.getStart_date().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)) : null)
            .endDate(tender.getEnd_date() != null ? tender.getEnd_date().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)) : null)
            .publishDate(tender.getPublish_date() != null ? tender.getPublish_date().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)) : null)
            .company(tender.getCompany())
            .link(tender.getLink())
            .startPrice(tender.getStart_price())
            .code(tender.getCode())
            .build();
    }
}


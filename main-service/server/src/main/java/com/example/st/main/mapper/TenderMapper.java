package com.example.st.main.mapper;

import org.example.st.dto.TenderDto;
import org.example.st.model.Tender;

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
            .startDate(tender.getStartDate() != null ? tender.getStartDate().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)) : null)
            .endDate(tender.getEndDate() != null ? tender.getEndDate().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)) : null)
            .publishDate(tender.getPublishDate() != null ? tender.getPublishDate().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)) : null)
            .company(tender.getCompany())
            .link(tender.getLink())
            .startPrice(tender.getStartPrice())
            .code(tender.getCode())
            .build();
    }


}


package com.example.st.main.mapper;

import org.example.st.model.Tender;
import org.example.st.dto.NewTenderDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс, отвечающий за преобразование между объектами TenderDto и Tender.
 */
public class NewTenderMapper {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Преобразует объект TenderDto в объект Tender.
     *
     * @param tenderDto объект TenderDto
     * @return объект Tender
     */
    public static Tender toTender(NewTenderDto tenderDto) {
        return Tender.builder()
            .currency(tenderDto.getCurrency())
            .status(tenderDto.getStatus())
            .name(tenderDto.getName())
            .startDate(LocalDateTime.parse(tenderDto.getStartDate(), DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)))
            .endDate(LocalDateTime.parse(tenderDto.getEndDate(), DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)))
            .publishDate(LocalDateTime.parse(tenderDto.getPublishDate(), DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)))
            .company(tenderDto.getCompany())
            .link(tenderDto.getLink() != null ? tenderDto.getLink() : null)
            .startPrice(tenderDto.getStartPrice() != null ? tenderDto.getStartPrice() : null)
            .code(tenderDto.getCode() != null ? tenderDto.getCode() : null)
            .build();
    }
}

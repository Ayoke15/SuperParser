package com.example.st.main.service;

import com.example.st.main.dto.NewTenderDto;
import com.example.st.main.dto.TenderDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Интерфейс, предоставляющий методы для работы с тендерами.
 */
public interface TenderService {

    /**
     * Получает список всех тендеров в виде объектов TenderDto.
     *
     * @param page Объект Pageable, содержащий информацию о странице
     *
     * @return список всех тендеров
     */
    List<TenderDto> getAllTenders(Pageable page);

    /**
     * Добавляет новый тендер на основе переданного объекта TenderDto.
     *
     * @param newTenderDto объект TenderDto, содержащий информацию о новом тендере
     */
    void postTender(NewTenderDto newTenderDto);
}

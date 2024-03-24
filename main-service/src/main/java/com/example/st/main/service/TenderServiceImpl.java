package com.example.st.main.service;

import com.example.st.main.dto.NewTenderDto;
import com.example.st.main.dto.TenderDto;
import com.example.st.main.exception.ConflictException;
import com.example.st.main.mapper.NewTenderMapper;
import com.example.st.main.mapper.TenderMapper;
import com.example.st.main.model.Tender;
import com.example.st.main.repository.TenderJpaRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс, предоставляющий реализацию методов для работы с тендерами.
 */
@Service
@Slf4j
@AllArgsConstructor
public class TenderServiceImpl implements TenderService {
    private TenderJpaRepository tenderJpaRepository;

    /**
     * Получает список всех тендеров в виде объектов TenderDto.
     *
     * @param page объект Pageable, содержащий информацию о странице и сортировке
     * @return список всех тендеров
     */
    @Override
    public List<TenderDto> getAllTenders(Pageable page) {
        List<TenderDto> tenderDtos = tenderJpaRepository.findAll().stream().map(TenderMapper::toTenderDto).toList();
        log.info("get-all-tenders request completed for tenderList:{}", tenderDtos);
        return tenderDtos;
    }

    /**
     * Добавляет новый тендер на основе переданного объекта NewTenderDto.
     *
     * @param newTenderDto объект NewTenderDto, содержащий информацию о новом тендере
     */
    @Override
    public void postTender(NewTenderDto newTenderDto) {
        try {
            Tender tender = tenderJpaRepository.save(NewTenderMapper.toTender(newTenderDto));
            log.info("create-tender request completed for tender:{}", tender);
        } catch (ConstraintViolationException | DataIntegrityViolationException e) {
            throw new ConflictException(e.getMessage());
        }
    }

    /**
     * Добавляет новые тендеры на основе переданного списка объектов NewTenderDto.
     *
     * @param newTenderDtos список объектов NewTenderDto, содержащий информацию о новых тендерах.
     */
    @Override
    public void postTenderList(List<NewTenderDto> newTenderDtos) {
        try {
            tenderJpaRepository.saveAll(newTenderDtos.stream().map(NewTenderMapper::toTender).toList());
            log.info("create-tenders request completed for tender list with size {}", newTenderDtos.size());
        } catch (ConstraintViolationException | DataIntegrityViolationException e) {
            throw new ConflictException(e.getMessage());
        }
    }
}

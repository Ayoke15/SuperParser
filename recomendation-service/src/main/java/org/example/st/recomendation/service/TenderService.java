package org.example.st.recomendation.service;

import org.example.st.model.Tender;
import org.example.st.recomendation.model.TenderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenderService {
    @Autowired
    private TenderRepository tenderRepository;

    public List<Tender> filterTenders(TenderDto tenderDto) {
        // Предполагается, что если пользователь не установил какой-либо фильтр, мы игнорируем его в запросе
        return tenderRepository.findByProductAndQuantityGreaterThanEqualAndStartDateGreaterThanEqualAndEndDateLessThanEqualAndRegion(
                tenderDto.getProduct(),
                tenderDto.getQuantity(),
                tenderDto.getStartDate(),
                tenderDto.getEndDate(),
                tenderDto.getRegion()
        );
    }
}

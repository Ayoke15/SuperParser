package com.example.st.add.service.implementation;

import com.example.st.add.service.ActiveActionService;
import lombok.AllArgsConstructor;
import org.example.st.model.ActiveAction;
import com.example.st.add.repository.ActiveActionRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * Реализация сервиса для работы с действиями.
 */
@Service
@AllArgsConstructor
public class ActiveActionServiceImpl implements ActiveActionService {

    private final ActiveActionRepository activeActionRepository;

    /**
     * Сохраняет новое активное действие в базе данных.
     *
     * @param activeAction Новое действие для сохранения.
     * @return Сохраненное действие.
     */
    @Override
    public ActiveAction saveAction(ActiveAction activeAction) {
        return activeActionRepository.save(activeAction);
    }

    /**
     * Находит действие по его идентификатору.
     *
     * @param id Идентификатор действия.
     * @return Найденное действие, если оно существует, в противном случае возвращает null.
     */
    @Override
    public ActiveAction findActionById(Long id) {
        return activeActionRepository.findById(id).orElse(null);
    }
}

package com.example.st.add.service.implementation;

import com.example.st.add.service.ActiveActionService;
import lombok.AllArgsConstructor;
import org.example.st.model.ActiveAction;
import com.example.st.add.repository.ActiveActionRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ActiveActionServiceImpl implements ActiveActionService {
    private final ActiveActionRepository activeActionRepository;

    @Override
    public ActiveAction saveAction(ActiveAction activeAction) {
        return activeActionRepository.save(activeAction);
    }

    @Override
    public ActiveAction findActionById(Long id) {
        return activeActionRepository.findById(id).orElse(null);
    }
}

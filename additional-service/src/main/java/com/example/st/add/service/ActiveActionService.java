package com.example.st.add.service;

import com.example.st.add.model.ActiveAction;
import com.example.st.add.repository.ActiveActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActiveActionService {
    private final ActiveActionRepository activeActionRepository;

    @Autowired
    public ActiveActionService(ActiveActionRepository activeActionRepository) {
        this.activeActionRepository = activeActionRepository;
    }

    public ActiveAction saveAction(ActiveAction activeAction) {
        return activeActionRepository.save(activeAction);
    }
}

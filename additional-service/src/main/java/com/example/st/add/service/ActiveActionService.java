package com.example.st.add.service;

import org.example.st.model.ActiveAction;

import java.util.List;

public interface ActiveActionService {
    ActiveAction saveAction(ActiveAction activeAction);
    ActiveAction findActionById(Long id);
}

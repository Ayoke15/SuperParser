package com.example.st.add.service;


import com.example.st.add.model.Xpath;
import com.example.st.add.repository.XpathRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class XpathService {
    private final XpathRepository xpathRepository;

    @Autowired
    public XpathService(XpathRepository xpathRepository) {
        this.xpathRepository = xpathRepository;
    }

    public Xpath saveXpath(Xpath xpath) {
        return xpathRepository.save(xpath);
    }

    public List<Xpath> findAllXpaths() {
        return xpathRepository.findAll();
    }
}

package com.example.st.add.service;

import org.example.st.model.Xpath;

import java.util.List;

public interface XpathService {
    Xpath saveXpath(Xpath xpath);
    List<Xpath> findAllXpath();
}

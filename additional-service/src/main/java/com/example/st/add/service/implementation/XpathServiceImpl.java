package com.example.st.add.service.implementation;


import com.example.st.add.service.XpathService;
import lombok.AllArgsConstructor;
import org.example.st.model.Xpath;
import com.example.st.add.repository.XpathRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@AllArgsConstructor
public class XpathServiceImpl implements XpathService {
    private final XpathRepository xpathRepository;

    @Override
    public Xpath saveXpath(Xpath xpath) {
        return xpathRepository.save(xpath);
    }

    @Override
    public Xpath findXpathById(Long id) {
        return xpathRepository.findById(id).orElse(null);
    }

    @Override
    public Xpath findByWebsiteLink(String link) {
        return xpathRepository.findByLinkSite(link);
    }

    @Override
    public List<Xpath> findAllXpath() {
        return xpathRepository.findAll();
    }
}

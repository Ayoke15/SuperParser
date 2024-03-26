package com.example.st.add.service;

import org.example.st.dto.NewTenderDto;
import org.example.st.model.Xpath;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ParserService {
    ResponseEntity<Void> parseAllWebsites();
    List<NewTenderDto> parseWebsite(String websiteLink);
    List<NewTenderDto> parsePage(Xpath xpath);
    List<String> extractText(String xpath);
    List<String> extractLinks(String xpath);
    List<String> formatDates(List<String> dates);
}

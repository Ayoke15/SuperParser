package com.example.st.add.service;


import com.example.st.add.repository.StatusRepository;
import com.example.st.add.repository.XpathRepository;
import lombok.AllArgsConstructor;
import org.example.st.client.TenderCreationClient;
import org.example.st.dto.NewTenderDto;
import org.example.st.model.Currency;
import org.example.st.model.Tender;
import org.example.st.model.Xpath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ParserService {
    private final WebDriver webDriver;
    private final StatusRepository statusRepository;
    private final XpathRepository xpathRepository;
    private final TenderCreationClient client;


    public List<Tender> parseWebsite() {
//        boolean isLastPage = false;
//        while (!isLastPage) {
//            parsePage();
//            if (nextPageButton.isDisplayed()) {
//                nextPageButton.click();
//            } else {
//                isLastPage = true;
//            }
//        }
//        return tenderRepository.findAll();
        return null;
    }

    public ResponseEntity<Void> parsePage() {
        List<NewTenderDto> tenders = new ArrayList<>();
        List<Xpath> xpathList = xpathRepository.findAll();

        //TODO Поменять на Thread pool
        for (Xpath xpath : xpathList) {
            webDriver.get(xpath.getLink_site());

            WebElement tendersSwitch = webDriver.findElement(By.xpath("//*[@id=\"P562_BARGAINING_TYPE\"]/option[5]"));
            tendersSwitch.click();

            List<String> codes = extractText(xpath.getCode());
            List<String> statuses = extractText(xpath.getStatus());
            List<String> names = extractText(xpath.getName());
            List<String> startDates = formatDates(extractText(xpath.getStartDate()));
            List<String> endDates = formatDates(extractText(xpath.getEndDate()));
            List<String> publishDates = formatDates(extractText(xpath.getPublishDate()));
            List<String> companies = extractText(xpath.getCompany());
            List<String> links = extractText(xpath.getLink());

            for (int i = 0; i < codes.size(); i++) {
                NewTenderDto tenderDto = NewTenderDto.builder()
                        .code(codes.get(i))
                        .currency(Currency.RUB)
                        .status(statusRepository.findByName(statuses.get(i)))
                        .name(names.get(i))
                        .startDate(startDates.get(i))
                        .endDate(endDates.get(i))
                        .publishDate(publishDates.get(i))
                        .company(companies.get(i))
                        .link("test")
                        .build();
                tenders.add(tenderDto);
            }
        }
        return client.postTendersList(tenders);
    }

    private List<String> extractText(String xpath) {
        List<WebElement> elements = webDriver.findElements(By.xpath(xpath));
        List<String> elementTexts = new ArrayList<>();
        for (WebElement element : elements) {
            elementTexts.add(element.getText());
        }
        return elementTexts;
    }

    private List<String> formatDates(List<String> dates) {
        List<String> formattedDates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        for (String date : dates) {
            if (!date.isEmpty()) {
                LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
                String formattedDate = dateTime.format(formatter);
                formattedDates.add(formattedDate);
            } else {
                formattedDates.add(date);
            }
        }
        return formattedDates;
    }

}

package com.example.st.add.service;


import com.example.st.add.repository.StatusRepository;
import com.example.st.add.repository.XpathRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.st.client.TenderCreationClient;
import org.example.st.dto.NewTenderDto;
import org.example.st.model.Currency;
import org.example.st.model.Tender;
import org.example.st.model.Xpath;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ParserService {
    private final WebDriver webDriver;
    private final StatusRepository statusRepository;
    private final XpathRepository xpathRepository;
    private final TenderCreationClient client;


    //TODO Поменять на Thread pool
    public ResponseEntity<Void> parseAllWebsites() {
        List<Xpath> xpathList = xpathRepository.findAll();

        List<NewTenderDto> newTenderDtoList = new ArrayList<>();
        for (Xpath xpath : xpathList) {
            newTenderDtoList.addAll(parseWebsite(xpath.getLinkSite()));
        }
        for (NewTenderDto newTenderDto : newTenderDtoList) {
            System.out.println(newTenderDto);
        }
        return client.postTendersList(newTenderDtoList);
    }

    public List<NewTenderDto> parseWebsite(String websiteLink) {
        webDriver.get(websiteLink);

        Xpath xpath = xpathRepository.findByLinkSite(websiteLink);

        WebElement tendersSwitchButton = webDriver.findElement(By.xpath(xpath.getSwitchButton()));
        tendersSwitchButton.click();

        List<NewTenderDto> tendersFromWebsite = new ArrayList<>();
        boolean isLastPage = false;

        while (!isLastPage) {
            tendersFromWebsite.addAll(parsePage(xpath));
            try {
                WebElement nextPageButton = webDriver.findElement(By.xpath(xpath.getNextButton()));
                log.error("log1: {}", nextPageButton.getTagName());
                nextPageButton.click();
            } catch (NoSuchElementException e) {
                isLastPage = true;
            }
        }
        return tendersFromWebsite;
    }

    public List<NewTenderDto> parsePage(Xpath xpath) {

        List<NewTenderDto> tendersFromPage = new ArrayList<>();

        List<String> startDates = extractText(xpath.getStartDate());
        List<String> endDates = extractText(xpath.getEndDate());
        List<String> publishDates = extractText(xpath.getPublishDate());

        List<String> codes = extractText(xpath.getCode());
        List<String> statuses = extractText(xpath.getStatus());
        List<String> names = extractText(xpath.getName());
        startDates = formatDates(startDates);
        endDates = formatDates(endDates);
        publishDates = formatDates(publishDates);
        List<String> companies = extractText(xpath.getCompany());
//        List<String> links = extractText(xpath.getLink());

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
            tendersFromPage.add(tenderDto);
        }
        return tendersFromPage;
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

package com.example.st.add.service;

import com.example.st.add.model.Currency;
import com.example.st.add.model.Status;
import com.example.st.add.model.Tender;
import com.example.st.add.model.Xpath;
import com.example.st.add.repository.StatusRepository;
import com.example.st.add.repository.TenderRepository;
import com.example.st.add.repository.XpathRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ParserService {
    private final WebDriver webDriver;
    private final StatusRepository statusRepository;
    private final TenderRepository tenderRepository;
    private final XpathRepository xpathRepository;

    @Autowired
    public ParserService(WebDriver webDriver,
                         StatusRepository statusRepository,
                         TenderRepository tenderRepository,
                         XpathRepository xpathRepository) {
        this.webDriver = webDriver;
        this.statusRepository = statusRepository;
        this.tenderRepository = tenderRepository;
        this.xpathRepository = xpathRepository;
    }

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
        return tenderRepository.findAll();
    }

//    public List<Tender> parsePage() {
//        List<Tender> tenders = new ArrayList<>();
//        List<Xpath> xpathList = xpathRepository.findAll();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
//
//        //TODO Поменять на Thread pool
//        for (Xpath xpath : xpathList) {
//            webDriver.get(xpath.getLink_site());
//
//            WebElement tendersSwitch = webDriver.findElement(By.xpath("//*[@id=\"P562_BARGAINING_TYPE\"]/option[5]"));
//            tendersSwitch.click();
//
//            List<String> codes = extractText(xpath.getCode());
//            List<String> statuses = extractText(xpath.getStatus());
//            List<String> names = extractText(xpath.getName());
//            List<LocalDateTime> startDates = extractText(xpath.getStart_date());
//            List<LocalDateTime> endDates = extractText(xpath.getEnd_date())
//                    .stream()
//                    .filter(date -> !date.isEmpty())
//                    .map(date -> LocalDateTime.parse(date, formatter))
//                    .toList();
//            List<LocalDateTime> publishDates = extractText(xpath.getPublish_date())
//                    .stream()
//                    .filter(date -> !date.isEmpty())
//                    .map(date -> LocalDateTime.parse(date, formatter))
//                    .toList();
//
//            List<LocalDateTime> parsedStartDates = new ArrayList<>();
//            for (String date : startDates) {
//                if (!date.isEmpty()) {
//                    parsedStartDates.add(LocalDateTime.parse(date, formatter));
//                }
//            }
//            List<String> companies = extractText(xpath.getCompany());
//            List<String> links = extractText(xpath.getLink());
//
//            for (LocalDateTime date : startDates) {
//                System.out.println(date);
//            }
//
//            for (int i = 0; i < codes.size(); i++) {
//                Tender tender = Tender.builder()
//                        .code(codes.get(i))
//                        .currency(Currency.RUB)
//                        .status(statusRepository.findByName(statuses.get(i)))
//                        .name(names.get(i))
//                        .start_date(startDates.get(i))
//                        .end_date(endDates.get(i))
//                        .publish_date(publishDates.get(i))
//                        .company(companies.get(i))
//                        .link(links.get(i))
//                        .build();
//                tenders.add(tender);
//            }
//        }
//        return tenders;
////        return tenderRepository.saveAll(tenders);
//    }

    private List<String> extractText(String xpath) {
        List<WebElement> elements = webDriver.findElements(By.xpath(xpath));
        List<String> elementTexts = new ArrayList<>();
        for (WebElement element : elements) {
            elementTexts.add(element.getText());
        }
        return elementTexts;
    }

}

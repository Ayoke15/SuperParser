package com.example.st.add.service;


import com.example.st.add.repository.StatusRepository;
import com.example.st.add.repository.XpathRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.st.client.TenderCreationClient;
import org.example.st.dto.NewTenderDto;
import org.example.st.model.Currency;
import org.example.st.model.Tender;
import org.example.st.model.Xpath;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParserService {
    private final WebDriver webDriver;
    private final StatusRepository statusRepository;
    private final XpathRepository xpathRepository;
    private final TenderCreationClient client;
//    private WebElement copyButton;


    //TODO Поменять на Thread pool

    /**
     * Выполняет парсинг всех веб-сайтов, представленных в базе данных
     *
     * @return Ответ от сервера
     */
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

    /**
     * Выполняет парсинг определенного веб-сайта
     *
     * @param websiteLink строка, содержащая ссылку на веб-сайт
     * @return Список всех тендеров на сайте
     */
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
                Thread.sleep(5000);
            } catch (NoSuchElementException e) {
                isLastPage = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return tendersFromWebsite;
    }

    /**
     * Выполняет парсинг одной страницы веб-сайта.
     *
     * @param xpath объект Xpath, содержащий информацию о элементах на странице, которые необходимо спарсить
     * @return Список всех тендеров на странице
     */
    public List<NewTenderDto> parsePage(Xpath xpath) {
        List<NewTenderDto> tendersFromPage = new ArrayList<>();

        List<String> codes = extractText(xpath.getCode());
        List<String> names = extractText(xpath.getName());
        List<String> statuses = extractText(xpath.getStatus());
        List<String> companies = extractText(xpath.getCompany());
        List<String> startPrices = extractText(xpath.getStartPrice());
        List<String> publishDates = extractText(xpath.getPublishDate());
        List<String> startDates = extractText(xpath.getStartDate());
        List<String> endDates = extractText(xpath.getEndDate());

        startDates = formatDates(startDates);
        endDates = formatDates(endDates);
        publishDates = formatDates(publishDates);

        List<String> links = extractLinks(xpath.getLink());

        for (int i = 0; i < codes.size(); i++) {
            NewTenderDto tenderDto = NewTenderDto.builder()
                    .code(codes.get(i))
                    .currency(Currency.RUB)
                    .status(statusRepository.findByName(statuses.get(i)))
                    .name(names.get(i))
                    .startPrice(startPrices.get(i))
                    .startDate(startDates.get(i))
                    .endDate(endDates.get(i))
                    .publishDate(publishDates.get(i))
                    .company(companies.get(i))
                    .link(links.get(i))
                    .build();
            tendersFromPage.add(tenderDto);
        }
        return tendersFromPage;
    }

    /**
     * Извлекает текст из элементов на странице.
     *
     * @param xpath объект строки, содержащий Xpath конкретного элемента
     * @return Список строк, содержащих текст внутри элементов
     */
    private List<String> extractText(String xpath) {
//        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(100));
//
//        wait.until(d -> {
//            while (true) {
//                try {
//                    List<WebElement> elements = webDriver.findElements(By.xpath(xpath));
//                    List<String> elementTexts = new ArrayList<>();
//                    for (WebElement element : elements) {
//                        elementTexts.add(element.getText());
//                    }
//                    if (copyButton.isDisplayed()) {
//                        return true;
//                    }
//                    return false;
//                } catch (Exception e) {
//                    continue;
//                }
//            }
//        });
        List<WebElement> elements = webDriver.findElements(By.xpath(xpath));
        List<String> elementTexts = new ArrayList<>();
        for (WebElement element : elements) {
            elementTexts.add(element.getText());
        }

        return elementTexts;
    }

    /**
     * Извлекает ссылки из элементов на странице.
     *
     * @param xpath объект строки, содержащий Xpath конкретного элемента
     * @return Список строк, содержащих ссылки на страницы тендеров
     */
    private List<String> extractLinks(String xpath) {
        List<WebElement> linkElements = webDriver.findElements(By.xpath(xpath));
        List<String> links = new ArrayList<>();
        for (WebElement link : linkElements){
            links.add(link.getAttribute("href"));
        }
        return links;
    }

    /**
     * Приводит строки с датами к формату dd.MM.yyyy HH:mm
     *
     * @param dates список неотформатированных строк с датами
     * @return Список строк с датами в формате dd.MM.yyyy HH:mm
     */
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

package com.example.st.add.service.implementation;


import com.example.st.add.repository.StatusRepository;
import com.example.st.add.repository.XpathRepository;
import com.example.st.add.service.ParserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.st.client.TenderCreationClient;
import org.example.st.dto.NewTenderDto;
import org.example.st.model.ActiveAction;
import org.example.st.model.Currency;
import org.example.st.model.Status;
import org.example.st.model.Xpath;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Реализация сервиса для выполнения парсинга веб-сайтов.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ParserServiceImpl implements ParserService {
    private final WebDriver webDriver;
    private final StatusRepository statusRepository;
    private final XpathRepository xpathRepository;
    private final TenderCreationClient client;


    //TODO Поменять на Thread pool

    /**
     * Выполняет парсинг всех веб-сайтов, представленных в базе данных
     *
     * @return Ответ от сервера
     */
    @Override
    public ResponseEntity<Void> parseAllWebsites() {
        List<Xpath> xpathList = xpathRepository.findAll();

        xpathList.sort(Comparator.comparingLong(Xpath::getId));

        for (Xpath xpath : xpathList){
            parseWebsite(xpath.getLinkSite());
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Выполняет парсинг определенного веб-сайта
     *
     * @param websiteLink строка, содержащая ссылку на веб-сайт
     */
    @Override
    public void parseWebsite(String websiteLink) {
        webDriver.get(websiteLink);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        Xpath xpath = xpathRepository.findByLinkSite(websiteLink);

        List<ActiveAction> actions = xpath.getActiveActions();
        actions.sort(Comparator.comparingInt(ActiveAction::getNumber));

        for (ActiveAction action : actions) {
            WebElement actionElement = webDriver.findElement(By.xpath(action.getXpath_path()));
            Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            wait.until(d -> actionElement.isDisplayed());
            actionElement.click();
        }

        boolean isLastPage = false;

        while (!isLastPage) {
            parsePage(xpath);
            try {
                WebElement nextPageButton = webDriver.findElement(By.xpath(xpath.getNextButton()));
                log.error("log1: {}", nextPageButton.getTagName());
                nextPageButton.click();
                Thread.sleep(1000);
            } catch (NoSuchElementException e) {
                isLastPage = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ResponseEntity.ok().build();
    }

    /**
     * Выполняет парсинг одной страницы веб-сайта.
     *
     * @param xpath объект Xpath, содержащий информацию о элементах на странице, которые необходимо спарсить
     */
    @Override
    public void parsePage(Xpath xpath) {
        List<NewTenderDto> tendersFromPage = new ArrayList<>();

        List<String> codes = extractText(xpath.getCode());
        List<String> names = extractText(xpath.getName());
        List<String> statuses = extractText(xpath.getStatus());
        List<String> companies = extractText(xpath.getCompany());
        List<String> startPrices = extractText(xpath.getStartPrice());
        List<String> publishDates = extractText(xpath.getPublishDate());
        List<String> startDates = extractText(xpath.getStartDate());
        List<String> endDates = extractText(xpath.getEndDate());
        List<String> links = extractLinks(xpath.getLink());

        startDates = formatDates(startDates);
        endDates = formatDates(endDates);
        publishDates = formatDates(publishDates);

        if (parseStatuses(statuses)) {
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
        }
        client.postTendersList(tendersFromPage);
    }

    /**
     * Парсит список статусов и сохраняет те статусы, которые отсутствуют в репозитории.
     * Если статус уже существует в репозитории, он не будет повторно сохранен.
     *
     * @param statuses список статусов для парсинга и сохранения
     * @return true, если парсинг и сохранение статусов прошли успешно, в противном случае - false
     */
    public boolean parseStatuses(List<String> statuses) {
        Set<String> statusSet = new HashSet<>(statuses);
        Set<String> existingStatusNames = statusRepository.findAll().stream()
                .map(Status::getName)
                .collect(Collectors.toSet());

        statusSet.removeAll(existingStatusNames);

        if (!statusSet.isEmpty()) {
            List<Status> newStatuses = statusSet.stream()
                    .map(name -> Status.builder()
                            .name(name)
                            .build())
                    .toList();
            statusRepository.saveAll(newStatuses);
        }
        return true;
    }

    /**
     * Извлекает текст из элементов на странице.
     *
     * @param xpath объект строки, содержащий Xpath конкретного элемента
     * @return Список строк, содержащих текст внутри элементов
     */
    @Override
    public List<String> extractText(String xpath) {
        List<WebElement> elements = webDriver.findElements(By.xpath(xpath));
        List<String> elementTexts = new ArrayList<>();
        for (WebElement element : elements) {
            if (element.getTagName().equals("img")) {
                elementTexts.add(element.getAttribute("title"));
            } else {
                elementTexts.add(element.getText());
            }
        }

        return elementTexts;
    }

    /**
     * Извлекает ссылки из элементов на странице.
     *
     * @param xpath объект строки, содержащий Xpath конкретного элемента
     * @return Список строк, содержащих ссылки на страницы тендеров
     */
    @Override
    public List<String> extractLinks(String xpath) {
        List<WebElement> linkElements = webDriver.findElements(By.xpath(xpath));
        List<String> links = new ArrayList<>();
        for (WebElement link : linkElements) {
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
    @Override
    public List<String> formatDates(List<String> dates) {
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
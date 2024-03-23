package com.example.st.add.service;

import com.example.st.add.model.Currency;
import com.example.st.add.model.Tender;
import com.example.st.add.repository.StatusRepository;
import com.example.st.add.repository.TenderRepository;
import com.example.st.add.repository.XpathRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public List<Tender> parseTatneft() {
        webDriver.get("https://etp.tatneft.ru/pls/tzp/f?p=220:562:8376086794241::::P562_OPEN_MODE,GLB_NAV_ROOT_ID,GLB_NAV_ID:,12920020,12920020");
        WebElement tendersSwitch = webDriver.findElement(By.xpath("//*[@id=\"P562_BARGAINING_TYPE\"]/option[5]"));
        tendersSwitch.click();
        WebElement nextPageButton = webDriver.findElement(By.xpath("//*[@id=\"R25399004548800692_data_panel\"]/div[2]/ul/li[3]/button"));
        boolean isLastPage = false;

        while (!isLastPage) {
            parseTatneftPage();
            if (nextPageButton.isDisplayed()) {
                nextPageButton.click();
            } else {
                isLastPage = true;
            }
        }
        return tenderRepository.findAll();
    }

    public List<Tender> parseTatneftPage() {
        List<WebElement> rows = webDriver.findElements(By.xpath("//*[@id='25399118176800742']/tbody/tr[position() >= 2 and position() <= 15]"));

        List<Tender> tenders = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        for (WebElement row : rows) {
            List<WebElement> tenderInfo = row.findElements(By.tagName("td"));

            LocalDateTime publishDate = LocalDateTime.parse(tenderInfo.get(7).getText(), formatter);
            LocalDateTime startDate = !tenderInfo.get(8).getText().isEmpty() ? LocalDateTime.parse(tenderInfo.get(8).getText(), formatter) : null;
            LocalDateTime endDate = LocalDateTime.parse(tenderInfo.get(9).getText(), formatter);

            tenders.add(Tender.builder()
                    .code(tenderInfo.get(1).getText())
                    .name(tenderInfo.get(2).getText())
                    .status(statusRepository.findByName(tenderInfo.get(3).getText()))
                    .company(tenderInfo.get(4).getText())
                    .start_price(tenderInfo.get(5) != null ? tenderInfo.get(5).getText() : null)
                    .currency(Currency.RUB)
                    .publish_date(publishDate)
                    .start_date(startDate)
                    .end_date(endDate)
                    .build());
        }
        return tenderRepository.saveAll(tenders);
    }
}

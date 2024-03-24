package com.example.st.add.service;

import com.example.st.add.model.Currency;
import com.example.st.add.model.Tender;
import com.example.st.add.model.Xpath;
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
public class XpathService {
    private final XpathRepository xpathRepository;
    private final WebDriver webDriver;

    @Autowired
    public XpathService(XpathRepository xpathRepository, WebDriver webDriver) {
        this.xpathRepository = xpathRepository;
        this.webDriver = webDriver;
    }

    public Xpath saveXpathTatneft() {

        webDriver.get("etp.tatneft.ru");

        //Переход на страницу с торгами
        webDriver.findElement(By.xpath("//*[@id=\"menu\"]/table/tbody/tr/td[2]/nobr/ul/li[2]/a")).click();

        //Кнопка для переключения на тип торгов "Тендеры"
        WebElement tendersSwitch = webDriver.findElement(By.xpath("//*[@id=\"P562_BARGAINING_TYPE\"]/option[5]"));
        tendersSwitch.click();

        String columnXpath = "//*[@id='25399118176800742']/tbody/tr/td";
        String nextButtonXpath = "//*[@id=\"R25399004548800692_data_panel\"]/div[2]/ul/li[3]/button";

        Xpath xpath = Xpath.builder()
                .link_site("https://etp.tatneft.ru/pls/tzp/f?p=220:562:5015209375285::::P562_OPEN_MODE,GLB_NAV_ROOT_ID,GLB_NAV_ID:,12920020,12920020")
                .code(columnXpath)
                .name(columnXpath)
                .status(columnXpath)
                .company(columnXpath)
                .start_price(columnXpath)
                .currency(columnXpath)
                .publish_date(columnXpath)
                .start_date(columnXpath)
                .end_date(columnXpath)
                .next_button(nextButtonXpath)
                .build();

        return xpathRepository.save(xpath);
    }
//        List<WebElement> rows = webDriver.findElements(By.xpath("//*[@id='25399118176800742']/tbody/tr[position() >= 2 and position() <= 15]"));

//        List<Tender> tenders = new ArrayList<>();

//        Xpath xpath = new Xpath();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
//        for (WebElement row : rows) {
//            List<WebElement> tenderInfo = row.findElements(By.tagName("td"));
//
//            LocalDateTime publishDate = LocalDateTime.parse(tenderInfo.get(7).getText(), formatter);
//            LocalDateTime startDate = !tenderInfo.get(8).getText().isEmpty() ? LocalDateTime.parse(tenderInfo.get(8).getText(), formatter) : null;
//            LocalDateTime endDate = LocalDateTime.parse(tenderInfo.get(9).getText(), formatter);
//
//            tenders.add(Tender.builder()
//                    .code(tenderInfo.get(1).getText())
//                    .name(tenderInfo.get(2).getText())
//                    .status(statusRepository.findByName(tenderInfo.get(3).getText()))
//                    .company(tenderInfo.get(4).getText())
//                    .start_price(tenderInfo.get(5) != null ? tenderInfo.get(5).getText() : null)
//                    .currency(Currency.RUB)
//                    .publish_date(publishDate)
//                    .start_date(startDate)
//                    .end_date(endDate)
//                    .build());
//        }
//        return xpathRepository.save(xpath);
}

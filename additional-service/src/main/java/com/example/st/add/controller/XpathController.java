package com.example.st.add.controller;

import com.example.st.add.service.XpathService;
import lombok.AllArgsConstructor;
import org.example.st.model.Xpath;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для работы с объектами Xpath.
 */
@RestController
@RequestMapping("/xpath")
@AllArgsConstructor
public class XpathController {

    private final XpathService xpathService;

    /**
     * Метод для получения всех объектов Xpath.
     *
     * @return Список всех объектов Xpath.
     */
    @GetMapping("/all")
    public List<Xpath> getAll() {
        return xpathService.findAllXpath();
    }

    /**
     * Метод для получения объекта Xpath по его идентификатору.
     *
     * @param id Идентификатор объекта Xpath.
     * @return Объект Xpath с указанным идентификатором.
     */
    @GetMapping("/get-by-id")
    public Xpath getById(@RequestParam Long id) {
        return xpathService.findXpathById(id);
    }

    /**
     * Метод для получения объекта Xpath по ссылке на веб-сайт.
     *
     * @param link Ссылка на веб-сайт.
     * @return Объект Xpath с указанной ссылкой на веб-сайт.
     */
    @GetMapping("/get-by-link")
    public Xpath getByWebsite(@RequestParam String link) {
        return xpathService.findByWebsiteLink(link);
    }

    /**
     * Метод для добавления нового объекта Xpath.
     *
     * @param xpath Новый объект Xpath для добавления.
     * @return Добавленный объект Xpath.
     */
    @PostMapping("")
    public Xpath addXpath(@RequestBody Xpath xpath) {
        return xpathService.saveXpath(xpath);
    }

    /**
     * Метод для редактирования существующего объекта Xpath.
     *
     * @param id    Идентификатор объекта Xpath, который нужно отредактировать.
     * @param xpath Обновленная информация об объекте Xpath.
     * @return Отредактированный объект Xpath.
     */
    @PatchMapping("/edit")
    public Xpath editXpath(@RequestParam Long id,
                           @RequestBody Xpath xpath) {
        xpath.setId(id);
        return xpathService.saveXpath(xpath);
    }
}

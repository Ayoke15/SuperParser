package com.example.st.add.service.implementation;

import com.example.st.add.service.XpathService;
import lombok.AllArgsConstructor;
import org.example.st.model.Xpath;
import com.example.st.add.repository.XpathRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса для работы с объектами Xpath.
 */
@Service
@AllArgsConstructor
public class XpathServiceImpl implements XpathService {

    private final XpathRepository xpathRepository;

    /**
     * Сохраняет объект Xpath в базе данных.
     *
     * @param xpath Объект Xpath для сохранения.
     * @return Сохраненный объект Xpath.
     */
    @Override
    public Xpath saveXpath(Xpath xpath) {
        return xpathRepository.save(xpath);
    }

    /**
     * Находит объект Xpath по его идентификатору.
     *
     * @param id Идентификатор объекта Xpath.
     * @return Найденный объект Xpath, если он существует, в противном случае возвращает null.
     */
    @Override
    public Xpath findXpathById(Long id) {
        return xpathRepository.findById(id).orElse(null);
    }

    /**
     * Находит объект Xpath по ссылке на веб-сайт.
     *
     * @param link Ссылка на веб-сайт.
     * @return Найденный объект Xpath с указанной ссылкой на веб-сайт, если он существует, в противном случае возвращает null.
     */
    @Override
    public Xpath findByWebsiteLink(String link) {
        return xpathRepository.findByLinkSite(link);
    }

    /**
     * Возвращает список всех объектов Xpath.
     *
     * @return Список всех объектов Xpath.
     */
    @Override
    public List<Xpath> findAllXpath() {
        return xpathRepository.findAll();
    }
}

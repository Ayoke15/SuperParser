package org.example.st.client;

import org.example.st.dto.NewTenderDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;

/**
 * Класс-клиент для создания тендеров.
 */
@Service
public class TenderCreationClient {
    private static final String API_PREFIX = "/api";
    private static final String SERVER_URL = "http://localhost:8080";
    private final RestTemplate restTemplate;

    /**
     * Конструктор для инициализации клиента создания тендеров.
     *
     * @param serverUrl URL-адрес сервера
     * @param builder   Построитель RestTemplate
     */
    public TenderCreationClient(@Value(SERVER_URL) String serverUrl, RestTemplateBuilder builder) {
        this.restTemplate = builder
            .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
            .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
            .build();
    }

    /**
     * Отправляет список новых тендеров на сервер.
     *
     * @param tenderDtoList Список новых тендеров для создания
     * @return Ответ от сервера
     */
    public ResponseEntity<Void> postTendersList(List<NewTenderDto> tenderDtoList) {
        return restTemplate.postForEntity("/create-tenders", tenderDtoList, Void.class);
    }
}
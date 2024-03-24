package org.example.st.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

/**
 * Класс, представляющий сущность XPath.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "xpath")
public class Xpath {
    /**
     * Уникальный идентификатор XPath.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Ссылка на сайт.
     */
    @Column
    @NotNull
    private String link_site;

    /**
     * Валюта тендера.
     */
    @Column
    @NotNull
    private String currency;

    /**
     * Статус тендера.
     */
    @Column
    @NotNull
    private String status;

    /**
     * Название тендера.
     */
    @Column
    @NotNull
    private String name;

    /**
     * Дата начала тендера.
     */
    @Column
    @NotNull
    private String startDate;

    /**
     * Дата окончания тендера.
     */
    @Column
    @NotNull
    private String endDate;

    /**
     * Дата публикации тендера.
     */
    @Column
    private String publishDate;

    /**
     * Название компании, проводящей тендер.
     */
    @Column
    @NotNull
    private String company;

    /**
     * Ссылка на тендер.
     */
    @Column
    private String link;

    /**
     * Начальная цена тендера.
     */
    @Column
    private String start_price;

    /**
     * Код тендера.
     */
    @Column
    private String code;

    /**
     * Кнопка перехода на следующую страницу таблицы.
     */
    @Column
    private String next_button;

    /**
     * Имя пользователя для авторизации.
     */
    @Column
    private String username;

    /**
     * Пароль для авторизации.
     */
    @Column
    private String password;

    /**
     * Ссылка для авторизации.
     */
    @Column
    private String login_link;

    /**
     * Кнопка для авторизации.
     */
    @Column
    private String login_button;

    @OneToMany
    @NotNull
    private List<ActiveAction> activeActions;



}
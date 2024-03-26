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
    @Column(name = "link_site")
    @NotNull
    private String linkSite;

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
    @Column(name = "start_date")
    @NotNull
    private String startDate;

    /**
     * Дата окончания тендера.
     */
    @Column(name = "end_date")
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
    @Column(name = "start_price")
    private String startPrice;

    /**
     * Код тендера.
     */
    @Column
    private String code;

    /**
     * Кнопка перехода на следующую страницу таблицы.
     */
    @Column(name = "next_button")
    private String nextButton;

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
    @Column(name = "login_link")
    private String loginLink;

    /**
     * Кнопка для авторизации.
     */
    @Column(name = "login_button")
    private String loginButton;

    @Column(name = "switch_button")
    private String switchButton;

    @OneToMany
    @NotNull
    private List<ActiveAction> activeActions;
}

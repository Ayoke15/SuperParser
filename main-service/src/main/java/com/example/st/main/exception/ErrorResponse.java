package com.example.st.main.exception;

import lombok.Getter;

/**
 * Класс, представляющий объект ответа об ошибке.
 */
@Getter
public class ErrorResponse {
    /**
     * Сообщение об ошибке.
     */
    private final String message;

    /**
     * Конструктор класса ErrorResponse.
     *
     * @param message сообщение об ошибке
     */
    public ErrorResponse(String message) {
        this.message = message;
    }
}


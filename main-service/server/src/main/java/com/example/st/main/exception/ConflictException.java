package com.example.st.main.exception;

/**
 * Исключение, сигнализирующее о конфликтном состоянии данных.
 */
public class ConflictException extends RuntimeException {
    /**
     * Конструктор класса ConflictException.
     *
     * @param message сообщение об ошибке
     */
    public ConflictException(String message) {
        super(message);
    }
}

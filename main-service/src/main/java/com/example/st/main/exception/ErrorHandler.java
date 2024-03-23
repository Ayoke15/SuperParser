package com.example.st.main.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Класс, обрабатывающий исключения в контроллерах REST.
 */
@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    /**
     * Обрабатывает исключения, связанные с нарушением целостности данных в базе данных.
     *
     * @param e исключение, связанное с конфликтным состоянием данных
     * @return объект ResponseEntity с информацией об ошибке и HTTP статусом CONFLICT
     */
    @ExceptionHandler({
        org.hibernate.exception.ConstraintViolationException.class,
        DataIntegrityViolationException.class,
        ConflictException.class
    })
    ResponseEntity<ErrorResponse> handleDatabaseExceptions(final ConflictException e) {
        log.error("Exception: " + e.getMessage(), e);
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.CONFLICT);
    }
}
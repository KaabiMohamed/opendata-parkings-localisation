package com.instantsytem.parkingservice.config;

import com.instantsytem.parkingservice.dto.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.OffsetDateTime;

/**
 * This Class Handle Exception
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * Handle MethodArgumentTypeMismatchException
     * @param ex is thrown on controller
     * @return Error Message
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new Error()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(ex.getMessage())
                    .error(ex.getErrorCode())
                    .timestamp(OffsetDateTime.now())
            );
    }

    /**
     * Catch any exception
     * @param ex any exception
     * @return Error Message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAnyException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Error()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(ex.getMessage())
                        .timestamp(OffsetDateTime.now())
                );
    }

}
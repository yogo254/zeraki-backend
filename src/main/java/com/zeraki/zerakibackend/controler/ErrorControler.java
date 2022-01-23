package com.zeraki.zerakibackend.controler;

import java.sql.SQLIntegrityConstraintViolationException;

import com.zeraki.zerakibackend.app.exception.NoSuchElementFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorControler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleSQLIntegrityConstraintViolationException(
            SQLIntegrityConstraintViolationException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)

                .body(exception.getMessage());
    }

    @ExceptionHandler(NoSuchElementFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleNoSuchElementFoundException(
            NoSuchElementFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)

                .body(exception.getMessage());
    }

}

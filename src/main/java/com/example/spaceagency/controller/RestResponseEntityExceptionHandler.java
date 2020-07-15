package com.example.spaceagency.controller;

import com.example.spaceagency.exception.AppUserNotFoundException;
import com.example.spaceagency.exception.FileDbNotFoundException;
import com.example.spaceagency.exception.MissionAlredyExistException;
import com.example.spaceagency.exception.ProductNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LogManager.getLogger(RestResponseEntityExceptionHandler.class);

    public RestResponseEntityExceptionHandler() {
        super();
    }

    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class, NullPointerException.class
            , NoSuchElementException.class, JsonProcessingException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value
            = {EmptyResultDataAccessException.class})
    protected ResponseEntity<Object> handleInternalServerError(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value
            = {DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleBadRequsetError(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {AppUserNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(AppUserNotFoundException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        logger.error("AppUser not found");
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {FileDbNotFoundException.class})
    protected ResponseEntity<Object> handleFileDbNotFoundException(AppUserNotFoundException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        logger.error("FileDb not found");
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {ProductNotFoundException.class})
    protected ResponseEntity<Object> handleProductNotFoundException(AppUserNotFoundException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        logger.error("Product not found");
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {MissionAlredyExistException.class})
    protected ResponseEntity<Object> missionNameAlreadyExist(MissionAlredyExistException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        logger.error("Mission name already exists");
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
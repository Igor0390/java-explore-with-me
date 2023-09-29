package ru.practicum.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.CONFLICT;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    // 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.debug(e.toString());
        return ApiError.builder()
                .errors(Arrays.stream(e.getStackTrace())
                        .map(StackTraceElement::toString)
                        .collect(Collectors.toList()))
                .status(BAD_REQUEST)
                .reason("IНеправильно составлен запрос")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(ServerWebInputException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiError handleServerWebInputException(final ServerWebInputException e) {
        log.debug(e.toString());
        return ApiError.builder()
                .errors(Arrays.stream(e.getStackTrace())
                        .map(StackTraceElement::toString)
                        .collect(Collectors.toList()))
                .message(e.getMessage())
                .reason(e.getReason())
                .status(BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiError handleMissingServletRequestParameterException(final MissingServletRequestParameterException e) {
        log.debug(e.toString());
        return ApiError.builder()
                .errors(Arrays.stream(e.getStackTrace())
                        .map(StackTraceElement::toString)
                        .collect(Collectors.toList()))
                .message(e.getMessage())
                .reason(NestedExceptionUtils.getMostSpecificCause(e).getMessage())
                .status(BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    public ApiError handleValidationException(ValidationRequestException e) {
        return ApiError.builder()
                .errors(Arrays.stream(e.getStackTrace())
                        .map(StackTraceElement::toString)
                        .collect(Collectors.toList()))
                .status(BAD_REQUEST)
                .reason("Неправильно составлен запрос")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    // 404
    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(NOT_FOUND)
    public ApiError handleNoSuchElementException(final NoSuchElementException e) {
        return ApiError.builder()
                .errors(Arrays.stream(e.getStackTrace())
                        .map(StackTraceElement::toString)
                        .collect(Collectors.toList()))
                .status(NOT_FOUND)
                .reason("Object not found")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ApiError handleNotFound(final NotFoundException e) {
        log.debug(e.toString());
        return ApiError.builder()
                .errors(Arrays.stream(e.getStackTrace())
                        .map(StackTraceElement::toString)
                        .collect(Collectors.toList()))
                .status(NOT_FOUND)
                .reason("Нужный объект не найден")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    // 409
    @ExceptionHandler
    @ResponseStatus(CONFLICT)
    public ApiError handleConflictException(ConflictException e) {
        log.debug(e.toString());
        return ApiError.builder()
                .errors(Arrays.stream(e.getStackTrace())
                        .map(StackTraceElement::toString)
                        .collect(Collectors.toList()))
                .status(CONFLICT)
                .reason("Условия для запрошенной операции не выполнены")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(NonTransientDataAccessException.class)
    @ResponseStatus(CONFLICT)
    public ApiError handleNonTransientDataAccessException(final NonTransientDataAccessException e) {
        log.debug(e.toString());
        return ApiError.builder()
                .errors(Arrays.stream(e.getStackTrace())
                        .map(StackTraceElement::toString)
                        .collect(Collectors.toList()))
                .message(e.getCause().getMessage())
                .reason(NestedExceptionUtils.getMostSpecificCause(e).getMessage())
                .status(CONFLICT)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // 500
    @ExceptionHandler({Throwable.class})
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ApiError handleException(final Throwable e) {
        log.debug(e.toString());
        return ApiError.builder()
                .errors(Arrays.stream(e.getStackTrace())
                        .map(StackTraceElement::toString)
                        .collect(Collectors.toList()))
                .status(INTERNAL_SERVER_ERROR)
                .reason("На сервере возникла непредвиденная ситуация, которая не позволила ему выполнить запрос")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}

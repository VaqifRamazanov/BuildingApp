package com.matrix.buildingapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.sasl.AuthenticationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleNotFound(NotFoundException ex){
        log.error("Action.error.log -> NOT_FOUND: {}",ex.getMessage());
        return ExceptionDto.builder().message(ex.getMessage()).code(HttpStatus.NOT_FOUND.value()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionDto handle(MethodArgumentNotValidException ex){
        log.error("Action.error.log -> Already Taken: {}", ex.getMessage());
        return ExceptionDto.builder()
                .message("Validation failed: " + ex.getBody().getDetail())
                .code(HttpStatus.BAD_REQUEST.value()).build();
    }

    @ExceptionHandler({AlreadyExistException.class})
    public ExceptionDto handleStudentAlreadyExistsException(AlreadyExistException ex) {
        log.error("Action.error.log -> Already Taken: {}", ex.getMessage());
        return ExceptionDto.builder().message(ex.getMessage()).code(HttpStatus.CONFLICT.value()).build();
    }

    @ExceptionHandler(InvalidBalanceException.class)
    public ExceptionDto handleInvalidException(InvalidBalanceException e) {
        log.error("ActionLog.error.log -> Already Taken: {}", e.getMessage());
        return ExceptionDto.builder().message(e.getMessage()).code(HttpStatus.BAD_REQUEST.value()).build();
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ExceptionDto handleAccessDeniedException(AccessDeniedException e) {
        log.error("ActionLog is failed: {}", e.getMessage());
        return  ExceptionDto.builder().message(e.getMessage()).code(HttpStatus.FORBIDDEN.value()).build();
    }
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionDto handleAuthenticatedException(AuthenticationException e) {
        log.error("ActionLog error " + e.getMessage());
        return ExceptionDto.builder().message(e.getMessage()).code(HttpStatus.UNAUTHORIZED.value()).build();
    }


}

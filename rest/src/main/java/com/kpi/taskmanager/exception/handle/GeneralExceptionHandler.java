package com.kpi.taskmanager.exception.handle;

import com.kpi.taskmanager.exception.EntityException;
import com.kpi.taskmanager.exception.MaxNumberExceededException;
import com.kpi.taskmanager.exception.NotOwnerException;
import com.kpi.taskmanager.model.message.DefaultErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityException.class})
    ResponseEntity<Object> entityError(Exception exception, WebRequest request) {
        final DefaultErrorMessage message = new DefaultErrorMessage(HttpStatus.BAD_REQUEST.getReasonPhrase(), exception.getMessage());
        return this.handleExceptionInternal(exception,
                message,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    @ExceptionHandler({MaxNumberExceededException.class, NotOwnerException.class})
    ResponseEntity<Object> entityNumberError(Exception exception, WebRequest request) {
        final DefaultErrorMessage message = new DefaultErrorMessage(HttpStatus.FORBIDDEN.getReasonPhrase(), exception.getMessage());
        return this.handleExceptionInternal(exception,
                message,
                new HttpHeaders(),
                HttpStatus.FORBIDDEN,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return this.entityError(new EntityException(ex.getAllErrors().get(0).getDefaultMessage()), request);
    }
}

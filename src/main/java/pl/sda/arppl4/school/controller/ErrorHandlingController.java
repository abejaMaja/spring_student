package pl.sda.arppl4.school.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sda.arppl4.school.exception.CustomException;
import pl.sda.arppl4.school.model.dto.ErrorMessageResponse;


import javax.persistence.EntityNotFoundException;

@Slf4j
@RestControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> handleEntityNotFound(EntityNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessageResponse(exception.getMessage()));

    }

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.GONE)
    public ErrorMessageResponse handleCarNotAvailable(CustomException exception){
        return new ErrorMessageResponse(exception.getMessage());
    }
}

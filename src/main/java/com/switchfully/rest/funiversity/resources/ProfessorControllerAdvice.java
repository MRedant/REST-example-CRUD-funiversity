package com.switchfully.rest.funiversity.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = {"com.switchfully.rest.funiversity"})
public class ProfessorControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object illegalArgumentException(final IllegalArgumentException exception) {
        return new ResponseEntity<>(new MyError(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private class MyError {
        private String errorMessage;

        private MyError(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }


}

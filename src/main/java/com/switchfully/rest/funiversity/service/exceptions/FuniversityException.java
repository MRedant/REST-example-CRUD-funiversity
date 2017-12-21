package com.switchfully.rest.funiversity.service.exceptions;

public abstract class FuniversityException extends RuntimeException {

    public FuniversityException(String message) {
        super(message);
    }

}

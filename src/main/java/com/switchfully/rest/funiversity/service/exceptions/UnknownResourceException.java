package com.switchfully.rest.funiversity.service.exceptions;

public class UnknownResourceException extends FuniversityException {

    public UnknownResourceException(String field, String resource) {
        super(String.format("We could not find a %s for the provided %s", resource, field));
    }
}

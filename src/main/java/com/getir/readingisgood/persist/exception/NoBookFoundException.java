package com.getir.readingisgood.persist.exception;

public class NoBookFoundException extends BaseException {

    public NoBookFoundException(String id) {
        super("No Book found for id ".concat(id));
    }
}

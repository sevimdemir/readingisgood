package com.getir.readingisgood.persist.exception;

public class UserAlreadyExistsException extends BaseException {

    public UserAlreadyExistsException(String email) {
        super("A user is already exists with this email ".concat(email));
    }
}

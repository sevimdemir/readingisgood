package com.getir.readingisgood.persist.exception;

public class LoginFailedException extends BaseException {
    public LoginFailedException(String email) {
        super("Login attemp is failed for ".concat(email));
    }
}

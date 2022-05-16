package com.getir.readingisgood.exception;

public class NotEnoughCopyException extends BaseException {

    public NotEnoughCopyException(String id, Integer remaining) {
        super("Not enough Book available for ".concat(id)
                .concat(" remaining ".concat(remaining.toString())));
    }
}

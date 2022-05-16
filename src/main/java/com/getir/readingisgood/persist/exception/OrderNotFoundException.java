package com.getir.readingisgood.persist.exception;

public class OrderNotFoundException extends BaseException {

    public OrderNotFoundException(String id) {
        super("No order found for id ".concat(id));
    }
}

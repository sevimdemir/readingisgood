package com.getir.readingisgood.persist.exception;

public class LockingFailedException extends BaseException {
    public LockingFailedException() {
        super("Lock attemp is failed ");
    }
}

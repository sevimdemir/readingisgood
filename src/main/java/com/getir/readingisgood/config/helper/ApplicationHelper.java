package com.getir.readingisgood.config.helper;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

@Component
public class ApplicationHelper {

    @Bean
    public ReentrantLock locker() {
        return new ReentrantLock();
    }
}

package com.epam.Incrementor.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class CountService {

    private final AtomicLong atomicLong = new AtomicLong();

    public void incrementCounter() {
        atomicLong.incrementAndGet();
    }

    public Long getCounter() {
        return atomicLong.get();
    }

}
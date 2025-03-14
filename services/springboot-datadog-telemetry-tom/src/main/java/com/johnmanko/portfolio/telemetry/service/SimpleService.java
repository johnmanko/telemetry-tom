package com.johnmanko.portfolio.telemetry.service;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.aop.MeterTag;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Service
public class SimpleService {

    @Async
    @Timed(value = "getHello", description = "Get Hello World", extraTags = {"service", "SimpleService", "async", "true"})
    public CompletableFuture<String> getHello(@MeterTag(key = "hello.requestedDelay", expression = "'Requested Delay:' + #root ") long requestedDelay) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(requestedDelay);
            } catch (InterruptedException e) {

            }
            return "Hello World";
        });

    }

}

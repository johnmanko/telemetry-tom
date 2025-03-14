package com.johnmanko.portfolio.telemetry.rest;

import com.johnmanko.portfolio.telemetry.service.SimpleService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.observation.annotation.Observed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api")
public class SimpleRestController {

    @Autowired
    private SimpleService simpleService;

    @Value("150")
    private long requestDelay;

    @Observed(name = "getHello", contextualName = "getHello")
    @Timed
    @GetMapping(value = "/hello", produces = "text/plain")
    public CompletableFuture<String> getHello() {
        return simpleService.getHello(requestDelay);
    }

}

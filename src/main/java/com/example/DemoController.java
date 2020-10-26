package com.example;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.inject.Inject;

@Controller
public class DemoController {
    @Inject
    private SingletonService singletonService;

    @Inject
    private ContextService contextService;

    @Get(value="/", produces = MediaType.TEXT_PLAIN)
    public String getMessages() {
        return singletonService.getMessage() + " " + contextService.getMessage();
    }
}

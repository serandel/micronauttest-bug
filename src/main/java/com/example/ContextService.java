package com.example;

import io.micronaut.context.annotation.Context;

@Context
public class ContextService {
    public String getMessage() {
        return "REAL";
    }
}

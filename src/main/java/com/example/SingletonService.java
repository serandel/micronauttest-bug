package com.example;

import javax.inject.Singleton;

@Singleton
public class SingletonService {
    public String getMessage() {
        return "REAL";
    }
}

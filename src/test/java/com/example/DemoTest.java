package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.inject.Inject;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
public class DemoTest {
    @Inject
    EmbeddedServer server;

    @Inject
    @Client("/")
    HttpClient client;

    @Inject
    SingletonService singletonService;

    @Inject
    ContextService contextService;

    @MockBean(SingletonService.class)
    SingletonService singletonService() {
        return mock(SingletonService.class);
    }

    @MockBean(ContextService.class)
    ContextService contextService() {
        return mock(ContextService.class);
    }

    @Test
    void testMocksAreInjected() {
        when(singletonService.getMessage()).thenReturn("MOCK");
        when(contextService.getMessage()).thenReturn("MOCK");

        String response = client.toBlocking()
                .retrieve(HttpRequest.GET("/"));

        // Getting 'org.mockito.exceptions.misusing.MissingMethodInvocationException at DemoTest.java:46' because we
        // don't get a MockBean but the real one
        Assertions.assertEquals("MOCK MOCK", response);
    }
}

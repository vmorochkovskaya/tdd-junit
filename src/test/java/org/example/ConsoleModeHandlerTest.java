package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ConsoleModeHandlerTest {
    @InjectMocks
    private ConsoleModeHandler consoleModeHandler;

    @Mock
    private EmailTemplateGenerator generator;
    @Mock
    private BufferedReader bufferedReader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateTemplateFromConsoleShouldReturnTemplateWithVarsEnteredFromConsole() throws IOException {
        when(generator.generateTemplate("name", "John")).thenReturn("Hello John");
        when(bufferedReader.readLine()).thenReturn("name John");

        var result = consoleModeHandler.generateTemplateFromConsole(bufferedReader);

        verify(generator).generateTemplate("name", "John");
        assertEquals("Hello John", result);
    }
}

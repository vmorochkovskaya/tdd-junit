package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ConsoleModeHandlerTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @InjectMocks
    private ConsoleModeHandler consoleModeHandler;

    @Mock
    private EmailTemplateGenerator generator;
    @Mock
    private BufferedReader bufferedReader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    @Test
    void generateTemplateFromConsoleShouldReturnTemplateWithVarsEnteredFromConsole() throws IOException {
        when(generator.generateTemplate("name", "John")).thenReturn("Hello John");
        when(bufferedReader.readLine()).thenReturn("name John");

        consoleModeHandler.generateTemplateFromConsole(bufferedReader);

        verify(generator).generateTemplate("name", "John");
        assertEquals("Enter variable values in the format key1 value1 key2 value2 ...\r\n" +
                "Result Template: Hello John\r\n", outputStreamCaptor.toString());
    }
}

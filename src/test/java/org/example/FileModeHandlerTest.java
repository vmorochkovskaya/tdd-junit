package org.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import static org.mockito.Mockito.*;

public class FileModeHandlerTest {
    @Mock
    private EmailTemplateGenerator generator;
    @Mock
    private BufferedReader bufferedReader;
    @Mock
    private FileReader fileReader;
    @Mock
    private FileWriter fileWriter;

    @InjectMocks
    private FileModeHandler fileModeHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void generateTemplateFromFileShouldWriteToOutputFile() throws Exception {
        when(generator.generateTemplate("name", "John", "order", "12345")).thenReturn("Hello John, your order is 12345");
        when(bufferedReader.readLine()).thenReturn("name John order 12345");
        doNothing().when(fileWriter).write("Hello John, your order is 12345");

        fileModeHandler.generateTemplateFromFile(bufferedReader, fileWriter);

        verify(bufferedReader).readLine();
        verify(fileWriter).write("Hello John, your order is 12345");
        verify(generator).generateTemplate("name", "John", "order", "12345");
    }
}
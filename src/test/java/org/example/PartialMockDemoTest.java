package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.FileWriter;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class PartialMockDemoTest {
    @Mock
    private EmailTemplateGenerator generator;
    @Mock
    private BufferedReader bufferedReader;
    @Mock
    private FileWriter fileWriter;

    @InjectMocks
    private FileModeHandler fileModeHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void generateTemplateFromFileShouldCallRealTemplateGeneratorMethodSuccess() throws Exception {
        var templateField = ReflectionUtils.findFields(EmailTemplateGenerator.class, f -> f.getName().equals("template"),
                ReflectionUtils.HierarchyTraversalMode.TOP_DOWN).get(0);
        templateField.setAccessible(true);
        templateField.set(generator, "Hello #{name}, your order is #{order}");

        when(generator.generateTemplate("name", "John", "order", "12345")).thenCallRealMethod();
        when(bufferedReader.readLine()).thenReturn("name John order 12345");
        doNothing().when(fileWriter).write("Hello John, your order is 12345");

        fileModeHandler.generateTemplateFromFile(bufferedReader, fileWriter);

        verify(bufferedReader).readLine();
        verify(fileWriter).write("Hello John, your order is 12345");
        verify(generator).generateTemplate("name", "John", "order", "12345");
    }
}

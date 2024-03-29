package org.example;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleModeHandler {
    private final EmailTemplateGenerator generator;


    public ConsoleModeHandler(EmailTemplateGenerator generator) {
        this.generator = generator;
    }

    public void generateTemplateFromConsole(BufferedReader reader) throws IOException {
        System.out.println("Enter variable values in the format key1 value1 key2 value2 ...");
        var keyValuePairs = reader.readLine().split(" ");
        System.out.println("Result Template: " + generator.generateTemplate(keyValuePairs));
    }
}

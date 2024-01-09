package org.example;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileModeHandler {
    private final EmailTemplateGenerator generator;


    public FileModeHandler(EmailTemplateGenerator generator) {
        this.generator = generator;
    }

    public void generateTemplateFromFile(BufferedReader reader, FileWriter writer) throws IOException {
        String[] keyValuePairs = reader.readLine().split(" ");
        String result = generator.generateTemplate(keyValuePairs);
        writer.write(result);
    }
}

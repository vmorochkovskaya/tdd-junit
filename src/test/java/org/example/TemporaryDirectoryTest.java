package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;

public class TemporaryDirectoryTest {

    @Test
    public void generateTemplateFromFile_Success(@TempDir(cleanup = CleanupMode.NEVER) File tempDir) throws IOException {
        var template = "Hello #{name}, your order #{order} has been shipped.";
        var fileModeHandler = new FileModeHandler(new EmailTemplateGenerator(template));

        // Create temporary input file
        var inputFile = new File(tempDir, "input.txt");
        try (FileWriter writer = new FileWriter(inputFile)) {
            writer.write("name John order 123");
        }

        // Create temporary output file
        var outputFile = new File(tempDir, "output.txt");

        fileModeHandler.generateTemplateFromFile(new BufferedReader(new FileReader(inputFile.getAbsolutePath())),
        new FileWriter(outputFile));
    }
}

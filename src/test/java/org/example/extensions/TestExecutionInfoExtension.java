package org.example.extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Optional;

public class TestExecutionInfoExtension implements TestWatcher {
    private static final String OUTPUT_FILE_PATH = "test_execution_info.txt";

    @Override
    public void testSuccessful(ExtensionContext context) {
        logTestExecutionInfo(context, "SUCCESS");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logTestExecutionInfo(context, "FAILURE");
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        logTestExecutionInfo(context, "DISABLED");
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        logTestExecutionInfo(context, "ABORTED");
    }

    private void logTestExecutionInfo(ExtensionContext context, String status) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_FILE_PATH, true))) {
            writer.println("Test execution information:");
            writer.println("Status: " + status);
            writer.println("Test class: " + context.getTestClass().map(Class::getName).orElse("N/A"));
            writer.println("Test method: " + context.getTestMethod().map(Method::getName).orElse("N/A"));
            writer.println();
        } catch (IOException e) {
            System.err.println("Failed to write test execution information to file: " + e.getMessage());
        }
    }
}

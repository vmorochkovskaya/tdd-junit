package org.example;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class DynamicEmailGeneratorTest {
    @TestFactory
    Collection<DynamicTest> generateTemplateDynamicTests() {
        return Arrays.asList(
                dynamicTest("Template with all matched variables",
                        () -> testGenerateTemplate("Hello #{name}, your order #{order} has been shipped.",
                                new String[]{"name", "John", "order", "123"}, "Hello John, your order 123 has been shipped.")),
                dynamicTest("Template with redundant variables",
                        () -> testGenerateTemplate("Message: #{message}",
                                new String[]{"message", "Hello World", "name", "John"}, "Message: Hello World")),
                dynamicTest("Template without variables",
                        () -> testGenerateTemplate("Template without variables",
                                new String[]{}, "Template without variables"))
        );
    }

    private void testGenerateTemplate(String template, String[] input, String expected) {
        var generator = new EmailTemplateGenerator(template);

        var result = generator.generateTemplate(input);

        assertEquals(expected, result);
    }

}

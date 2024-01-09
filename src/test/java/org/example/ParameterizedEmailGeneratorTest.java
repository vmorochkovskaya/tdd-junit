package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterizedEmailGeneratorTest {
    @ParameterizedTest
    @MethodSource("generateTemplateTestData")
    void generateTemplate_Success(String template, String[] input, String expected) {
        var generator = new EmailTemplateGenerator(template);

        var result = generator.generateTemplate(input);

        assertEquals(expected, result);
    }

    private static Stream<Object[]> generateTemplateTestData() {
        return Stream.of(
                new Object[]{"Hello #{name}, your order #{order} has been shipped.", new String[]{"name", "John", "order", "123"}, "Hello John, your order 123 has been shipped."},
                new Object[]{"Message: #{message}", new String[]{"message", "Hello World", "name", "John"}, "Message: Hello World"}
        );
    }
}

package org.example;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailGeneratorTest {
    @Test
    void generateTemplateSuccess() {
        var generator = new EmailTemplateGenerator("Hello #{name}, your order #{order} has been shipped.");

        var result = generator.generateTemplate("name", "John", "order", "12345");

        assertEquals("Hello John, your order 12345 has been shipped.", result);
    }
}

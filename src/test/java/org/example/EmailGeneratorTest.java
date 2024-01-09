package org.example;


import org.example.exceptions.VariableNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailGeneratorTest {
    @Test
    void generateTemplateWithVarsForAllPlaceholdersShouldReturnTemplate() {
        var generator = new EmailTemplateGenerator("Hello #{name}, your order #{order} has been shipped.");

        var result = generator.generateTemplate("name", "John", "order", "12345");

        assertEquals("Hello John, your order 12345 has been shipped.", result);
    }

    @Test
    void generateTemplateWithVarsForNotAllPlaceholdersShouldThrowException() {
        var generator = new EmailTemplateGenerator("Hello #{name}, your order #{order} has been shipped.");

        assertThrows(VariableNotFoundException.class, () -> generator.generateTemplate("name", "John"));
    }

    @Test
    void generateTemplateWithExtraVarsShouldReturnTemplateWithIgnoredVars() {
        var generator = new EmailTemplateGenerator("Hello #{name}, your order #{order} has been shipped.");

        var result = generator.generateTemplate("name", "John", "order", "12345", "extra", "value");

        assertEquals("Hello John, your order 12345 has been shipped.", result);
    }

    @Test
    void generateTemplateWithSpecialCharactersShouldReturnTemplateWithThem() {
        var generator = new EmailTemplateGenerator("Hello #{name}, your order #{order} has been shipped.");

        var result = generator.generateTemplate("name", "#{John}", "order", "#{12345}");

        assertEquals("Hello #{John}, your order #{12345} has been shipped.", result);
    }
}

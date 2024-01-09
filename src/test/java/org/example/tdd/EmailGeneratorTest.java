package org.example.tdd;


import org.example.EmailTemplateGenerator;
import org.example.annotations.RegressionTest;
import org.example.annotations.SmokeTest;
import org.example.exceptions.VariableNotFoundException;
import org.example.extensions.TestExecutionInfoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SmokeTest
@ExtendWith(TestExecutionInfoExtension.class)
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

        var exception = assertThrows(VariableNotFoundException.class, () -> generator.generateTemplate("name", "John"));

        assertEquals("Value not provided for variable: order", exception.getMessage());
    }

    @Test
    @RegressionTest
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

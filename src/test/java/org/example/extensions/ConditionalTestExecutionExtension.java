package org.example.extensions;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ConditionalTestExecutionExtension implements ExecutionCondition {
    private static final String DISABLE_PROPERTY = "disableTest";

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        return shouldDisableTest() ? ConditionEvaluationResult.disabled("Test disabled on condition") :
                ConditionEvaluationResult.enabled("Test enabled");
    }

    private boolean shouldDisableTest() {
        // Check if the system property 'disableTest' is set
        return Boolean.parseBoolean(System.getProperty(DISABLE_PROPERTY, "false"));
    }
}

package org.example;

import org.example.exceptions.VariableNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailTemplateGenerator {
    private String template;

    public EmailTemplateGenerator() {
    }

    public EmailTemplateGenerator(String template) {
        this.template = template;
    }

    public String generateTemplate(String... keyValuePairs) {
        System.out.println("Generate email template is being called");

        var variables = extractVariables(template);
        var values = new HashMap<String, String>();

        // Populate values map from input parameters
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            values.put(keyValuePairs[i], keyValuePairs[i + 1]);
        }

        // Check if all required variables have values
        for (String variable : variables.keySet()) {
            if (!values.containsKey(variable)) {
                throw new VariableNotFoundException("Value not provided for variable: " + variable);
            }
        }

        // Replace placeholders with values
        var result = template;
        for (var entry : values.entrySet()) {
            String placeholder = "#{" + entry.getKey() + "}";
            result = result.replaceAll(Pattern.quote(placeholder), Matcher.quoteReplacement(entry.getValue()));
        }

        return result;
    }

    private Map<String, String> extractVariables(String template) {
        var variables = new HashMap<String, String>();
        Matcher matcher = Pattern.compile("#\\{(\\w+)}").matcher(template);

        while (matcher.find()) {
            variables.put(matcher.group(1), null);
        }

        return variables;
    }

}

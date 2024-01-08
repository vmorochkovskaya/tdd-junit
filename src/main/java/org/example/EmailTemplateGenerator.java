package org.example;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailTemplateGenerator {
    private final String template;

    public EmailTemplateGenerator(String template) {
        this.template = template;
    }

    public String generateTemplate(String... keyValuePairs) {
        var values = new HashMap<String, String>();

        // Populate values map from input parameters
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            values.put(keyValuePairs[i], keyValuePairs[i + 1]);
        }

        // Replace placeholders with values
        var result = template;
        for (var entry : values.entrySet()) {
            String placeholder = "#{" + entry.getKey() + "}";
            result = result.replaceAll(Pattern.quote(placeholder), Matcher.quoteReplacement(entry.getValue()));
        }

        return result;
    }

}

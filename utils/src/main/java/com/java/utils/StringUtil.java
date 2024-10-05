package com.java.utils;

import java.util.Map;
import java.util.Map.Entry;

public class StringUtil {

    /**
     * Reverses the given string.
     *
     * @param strToRev The string to reverse.
     * @return The reversed string.
     * @throws IllegalArgumentException if the input is null
     *                                  or contains only whitespaces.
     */
    public static String reverse(String strToRev) {
        // Throw exception if the string parameter is null or contain only whitespaces
        if (strToRev == null || strToRev.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "strToRev must not be null and should contain characters apart from spaces");
        }
        // If the stirng contains only 1 character, no point in reversing
        if (strToRev.length() == 1) {
            return strToRev;
        } else {
            /*
             * If the string parameter has many characters, use StringBuilder to manage the
             * immutable nature of String. This approach is better optimized than using a
             * loop because it minimizes the overhead of creating multiple objects every
             * time you append to a new string, and it dynamically expands the underlying
             * array as needed.
             */
            return new StringBuilder(strToRev).reverse().toString();
        }
    }

    /**
     * Interpolates a template string by replacing placeholders with corresponding
     * values from a map.
     *
     * @param template the string template containing placeholders ${} to be
     *                 replaced
     * @param values   a map containing key-value pairs for replacement
     * @return the interpolated string with placeholders replaced by their
     *         corresponding values
     * @throws IllegalArgumentException if the template is null or only contains
     *                                  whitespaces
     */
    public static String interpolate(String template, Map<String, String> values) {
        // Throw exception if the template parameter is null or contain only whitespaces
        if (template == null || template.trim().isEmpty()) {
            throw new IllegalArgumentException("template is null or contains only whitespaces");
        }

        if (values == null || values.isEmpty()) {
            // Nothing to replace, return the same template
            return template;
        } else {
            StringBuilder strBuilder = new StringBuilder(template);
            for (Entry<String, String> eSet : values.entrySet()) {
                String exactKey = "${" + eSet.getKey() + "}";
                int index = strBuilder.indexOf(exactKey);
                while (index != -1) {
                    strBuilder.replace(index, index + exactKey.length(), eSet.getValue());
                    index = strBuilder.indexOf(exactKey, index);
                }
            }
            return strBuilder.toString();
        }
    }
}

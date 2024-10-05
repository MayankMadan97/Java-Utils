package com.java.utils;

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
}

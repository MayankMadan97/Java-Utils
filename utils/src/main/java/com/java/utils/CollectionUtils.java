package com.java.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionUtils {

    /**
     * Merges two maps into one. If both maps contain the same key,
     * the value from mapB will either override the value from mapA
     * or will be ignored based on the override flag.
     *
     * @param <T>      The type of the values in the map.
     * @param mapA     The first map to merge, can be null or empty.
     * @param mapB     The second map to merge, can be null or empty.
     * @param override If true, values from mapB will override values from mapA if
     *                 the same key exists.
     *                 If false, existing values in mapA will not be replaced by
     *                 values from mapB.
     * @return A new map containing the merged entries from mapA and mapB.
     * @throws IllegalArgumentException if both maps are null or empty.
     */
    public static <T> Map<String, T> merge(Map<String, T> mapA, Map<String, T> mapB, boolean override) {
        // Throw exception in case both maps are null or empty
        if ((mapA == null || mapA.isEmpty()) && (mapB == null || mapB.isEmpty())) {
            throw new IllegalArgumentException("Parameters provided are null or empty");
        }
        Map<String, T> resultantMap = new HashMap<>();

        if (mapA != null) {
            resultantMap.putAll(mapA);
        }

        // Only need to traverse if there is something in the second map
        if (mapB != null) {
            for (Entry<String, T> eSet : mapB.entrySet()) {
                if (override) {
                    // If true, simply add to resultant, map automatically overrides the old value
                    // with new
                    resultantMap.put(eSet.getKey(), eSet.getValue());
                } else {
                    resultantMap.putIfAbsent(eSet.getKey(), eSet.getValue());
                }
            }
        }
        return resultantMap;
    }
}

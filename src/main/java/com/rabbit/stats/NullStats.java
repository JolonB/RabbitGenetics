package com.rabbit.stats;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class NullStats extends EntityStats {
    /**
     * Generate a rabbit with randomised stats
     */
    public NullStats() {
        super();
    }

    public SortedMap<String, Number> getStats() {
        return new TreeMap<>(); // Don't bother returning any stats
    }

    public Map<String, Number> getAllStats() {
        return new TreeMap<>(); // Don't bother returning any stats
    }
}
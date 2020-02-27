package com.rabbit.stats;

import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class EntityStats {
    protected static final Random RAND = new Random();

    /** The amount of time the entity has existed for */
    private long timeAlive = 0;

    /**
     * Generate an entity with randomised stats
     */
    public EntityStats() {
    }

    public EntityStats(Map<String, Float> stats) {
    }

    public long getTimeAlive() {
        return this.timeAlive;
    }

    /**
     * Returns a map containing the fields that may be relevant to show on a user
     * interface. The map is sorted by the name of the field.
     * 
     * @return a sorted map of relevant information
     */
    public SortedMap<String, Number> getStats() {
        SortedMap<String, Number> map = new TreeMap<>();
        map.put("Time alive", this.timeAlive);
        return map;
    }

    /**
     * Returns a map containing all of the fields indexed by their names.
     * 
     * @return a map containing all of the fields
     */
    public Map<String, Number> getAllStats() {
        Map<String, Number> map = new TreeMap<>();
        map.put("timeAlive", timeAlive);
        return map;
    }
}

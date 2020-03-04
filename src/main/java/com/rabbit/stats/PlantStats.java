package com.rabbit.stats;

import java.util.Map;
import java.util.SortedMap;

public class PlantStats extends EntityStats {
    /** The number of steps the plant will live for */
    private int lifespan = 5 + RAND.nextInt(5);

    protected PlantStats() {
        super();
    }

    public int getLifespan() {
        return this.lifespan;
    }

    @Override
    public SortedMap<String, Number> getStats() {
        SortedMap<String, Number> map = super.getStats();
        map.put("Lifespan", this.lifespan);
        return map;
    }

    @Override
    public Map<String, Number> getAllStats() {
        Map<String, Number> map = super.getAllStats();
        map.put("lifespan", this.lifespan);
        return map;
    }
}
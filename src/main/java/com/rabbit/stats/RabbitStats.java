package com.rabbit.stats;

import java.util.Map;
import java.util.SortedMap;

public class RabbitStats extends AnimalStats {
    /** Desire to reproduce */
    private float libido;
    /** Energy lost during breeding */
    private final float breedingEnergy;

    /**
     * Generate a rabbit with randomised stats
     */
    public RabbitStats() {
        super();
        this.breedingEnergy = RAND.nextFloat();
    }

    public RabbitStats(float breedingEnergy, float speed, float movementEnergy, float idleEnergy, float minEnergy,
            float maxEnergy, float vision, float metabolism, int generation) {
        super(speed, movementEnergy, idleEnergy, minEnergy, maxEnergy, vision, metabolism, generation);
        this.breedingEnergy = (breedingEnergy <= 1.0 || breedingEnergy >= 0.0) ? breedingEnergy : RAND.nextFloat();
    }

    public RabbitStats(Map<String, Float> stats, int generation) {
        this(stats.get("breedingEnergy"), stats, generation);
    }

    private RabbitStats(float breedingEnergy, Map<String, Float> stats, int generation) {
        super(stats, generation);
        this.breedingEnergy = (breedingEnergy <= 1.0 || breedingEnergy >= 0.0) ? breedingEnergy : RAND.nextFloat();
    }

    public float getLibido() {
        return this.libido;
    }

    public float getBreedingEnergy() {
        return this.breedingEnergy;
    }

    public SortedMap<String, Number> getStats() {
        SortedMap<String, Number> map = super.getStats();
        map.put("Libido", this.libido);
        return map;
    }

    public Map<String, Number> getAllStats() {
        Map<String, Number> map = super.getAllStats();
        map.put("libido", this.libido);
        map.put("breedingEnergy", this.breedingEnergy);
        return map;
    }

    /**
     * Returns a map containing all fields relevant to breeding indexed by their
     * names.
     * 
     * @return a map containing all fields relevant to breeding
     */
    public Map<String, Float> getBreedingStats() {
        Map<String, Float> map = super.getBreedingStats();
        map.put("breedingEnergy", this.breedingEnergy);
        return map;
    }
}
package com.rabbit.stats;

import java.util.Map;
import java.util.SortedMap;

public class RabbitStats extends AnimalStats {
    /** Desire to reproduce */
    private float libido;
    /** The amount the rabbit's libido increases per step */
    private final float libidoIncrement;
    /** Energy lost during breeding */
    private final float breedingEnergy;

    /**
     * Generate a rabbit with randomised stats
     */
    public RabbitStats() {
        super();
        this.libidoIncrement = randomFloat(0.0f, 0.05f);
        this.breedingEnergy = randomFloat(getIdleEnergy(), getMaxEnergy()) / 10.0f;
    }

    public RabbitStats(Map<String, Float> stats, int generation) {
        super(stats, generation);
        this.libidoIncrement = stats.get("libidoIncrement");
        this.breedingEnergy = stats.get("breedingEnergy");
    }

    public float getLibido() {
        return this.libido;
    }

    public float getBreedingEnergy() {
        return this.breedingEnergy;
    }

    public void increaseLibido() {
        this.libido += this.libidoIncrement;
    }

    @Override
    public SortedMap<String, Number> getStats() {
        SortedMap<String, Number> map = super.getStats();
        map.put("Libido", this.libido);
        return map;
    }

    @Override
    public Map<String, Number> getAllStats() {
        Map<String, Number> map = super.getAllStats();
        map.put("libido", this.libido);
        map.put("libidoIncrement", this.libidoIncrement);
        map.put("breedingEnergy", this.breedingEnergy);
        return map;
    }

    @Override
    public Map<String, Float> getBreedingStats() {
        Map<String, Float> map = super.getBreedingStats();
        map.put("libidoIncrement", this.libidoIncrement);
        map.put("breedingEnergy", this.breedingEnergy);
        return map;
    }
}
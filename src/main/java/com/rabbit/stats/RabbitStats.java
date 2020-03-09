package com.rabbit.stats;

import java.util.Map;
import java.util.SortedMap;

public class RabbitStats extends AnimalStats {
    /** Desire to reproduce */
    private float libido;
    /** The level of importance of the rabbits libido in deciding actions */
    private final float libidoImportance;
    /** The amount the rabbit's libido increases per step */
    private final float libidoIncrement;
    /** Energy lost during breeding */
    private final float breedingEnergy;
    /** The probability a rabbit will turn away from water */
    private final float aversionToWater; // The further an animal is from water, the lower this is

    /**
     * Generate a rabbit with randomised stats
     */
    public RabbitStats() {
        super();
        this.libidoImportance = randomFloat(0.0f, 1.0f);
        this.libidoIncrement = randomFloat(0.0f, 0.05f);
        this.breedingEnergy = randomFloat(getIdleEnergy(), 0.1f);
        this.aversionToWater = randomFloat(0.0f, 1.0f);
    }

    public RabbitStats(Map<String, Number> stats, int generation) {
        super(stats, generation);
        this.libidoImportance = (float) stats.get("libidoImportance");
        this.libidoIncrement = (float) stats.get("libidoIncrement");
        this.breedingEnergy = (float) stats.get("breedingEnergy");
        this.aversionToWater = (float) stats.get("aversionToWater");
    }

    public float getLibido() {
        return this.libido;
    }

    public float getLibidoImportance() {
        return this.libidoImportance;
    }

    public float getBreedingEnergy() {
        return this.breedingEnergy;
    }

    public void increaseLibido() {
        this.libido = Math.min(1.0f, this.libido + this.libidoIncrement);
    }

    public boolean canBreed() {
        return RAND.nextDouble() <= getLibido() && getEnergy() > getBreedingEnergy();
    }

    public boolean getWaterAversion(double distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("distance to water can never be less than or equal to 0");
        }
        return RAND.nextDouble() <= this.aversionToWater / distance;
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
        map.put("libidoImportance", this.libidoImportance);
        map.put("libidoIncrement", this.libidoIncrement);
        map.put("breedingEnergy", this.breedingEnergy);
        map.put("aversionToWater", this.aversionToWater);
        return map;
    }

    @Override
    public Map<String, Number> getBreedingStats() {
        Map<String, Number> map = super.getBreedingStats();
        map.put("libidoImportance", this.libidoImportance);
        map.put("libidoIncrement", this.libidoIncrement);
        map.put("breedingEnergy", this.breedingEnergy);
        map.put("aversionToWater", this.aversionToWater);
        return map;
    }
}
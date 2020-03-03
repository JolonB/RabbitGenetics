package com.rabbit.stats;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class AnimalStats extends EntityStats {
    /** Desire to eat */
    private float hunger = 1; // newborn animals immediately seek food
    /** Desire to move */
    private float activeness = 1; // newborn animals want to move immediately
    /** The animal's current energy level */
    private float energy = 1; // begins with full energy, but will immediately be set to maxEnergy
    /** Rate of change of hunger per step */
    private final float hungerIncrease;
    /** Minimum activeness. Activeness will drop to this value after movement */
    private final float speed;
    /** Energy lost during movement */
    private final float movementEnergy;
    /** Energy lost while stationary */
    private final float idleEnergy;
    /** The maximum energy before no more energy can be gained */
    private final float maxEnergy;
    /** Energy level at which death occurs */
    private final float minEnergy;
    /** The range at which an animal can see another entity */
    private final float vision;
    /** Food to energy conversion efficiency. Higher -> more energy/food */
    private final float metabolism;
    /** The generation of the animal (longest line of ancestors + 1) */
    private final int generation;

    /**
     * Generate random stats for an animal
     */
    protected AnimalStats() {
        super();
        this.hungerIncrease = randomFloat(0.0f, 0.1f);
        this.speed = randomFloat(0.0f, 1.0f);
        this.maxEnergy = randomFloat(0.5f, 1.0f); // random number from 0.5 to 1.0
        this.minEnergy = randomFloat(0.0f, 0.5f); // random number from 0.0 to 0.5
        this.movementEnergy = randomFloat(minEnergy, maxEnergy) / 10.0f;
        this.idleEnergy = randomFloat(0.0f, movementEnergy); // energy lost idle must be <= energy lost moving
        this.vision = randomFloat(0.0f, 1.0f);
        this.metabolism = randomFloat(0.0f, 1.0f);
        this.generation = 1;
    }

    protected AnimalStats(Map<String, Float> stats, int generation) {
        super(stats);
        this.hungerIncrease = stats.get("hungerIncrease");
        this.speed = stats.get("speed");
        this.movementEnergy = stats.get("movementEnergy");
        this.idleEnergy = stats.get("idleEnergy");
        this.maxEnergy = stats.get("maxEnergy");
        this.minEnergy = stats.get("minEnergy");
        this.vision = stats.get("vision");
        this.metabolism = stats.get("metabolism");
        this.generation = generation;
    }

    public float getHunger() {
        return this.hunger;
    }

    public float getActiveness() {
        return this.activeness;
    }

    public float getEnergy() {
        return this.energy;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getMovementEnergy() {
        return this.movementEnergy;
    }

    public float getIdleEnergy() {
        return this.idleEnergy;
    }

    public float getMinEnergy() {
        return this.minEnergy;
    }

    public float getMaxEnergy() {
        return this.maxEnergy;
    }

    public float getVision() {
        return this.vision;
    }

    public float getMetabolism() {
        return this.metabolism;
    }

    public int getGeneration() {
        return this.generation;
    }

    public void increaseEnergy(float increaseBy) {
        updateEnergy(increaseBy);
        this.energy = Math.min(this.energy, this.maxEnergy);
    }

    public void decreaseEnergy(float decreaseBy) {
        updateEnergy(-decreaseBy);
        this.energy = Math.max(this.energy, this.minEnergy);
    }

    private void updateEnergy(float updateBy) {
        this.energy += updateBy;
    }

    public void increaseHunger() {
        this.hunger = Math.min(this.hunger + this.hungerIncrease, 1.0f);
    }

    public void decreaseActiveness() {
        this.activeness = this.speed;
    }

    @Override
    public SortedMap<String, Number> getStats() {
        SortedMap<String, Number> map = super.getStats();
        map.put("Hunger", this.hunger);
        map.put("Activeness", this.activeness);
        map.put("Energy", (this.energy - this.minEnergy) / (this.maxEnergy - this.minEnergy));
        map.put("Generation", this.generation);
        return map;
    }

    /**
     * Returns a map containing all of the fields indexed by their names.
     * 
     * @return a map containing all of the fields
     */
    @Override
    public Map<String, Number> getAllStats() {
        Map<String, Number> map = super.getAllStats();
        map.put("hunger", this.hunger);
        map.put("activeness", this.activeness);
        map.put("energy", this.energy);
        map.put("hungerIncrease", this.hungerIncrease);
        map.put("speed", this.speed);
        map.put("movementEnergy", this.movementEnergy);
        map.put("idleEnergy", this.idleEnergy);
        map.put("maxEnergy", this.maxEnergy);
        map.put("minEnergy", this.minEnergy);
        map.put("vision", this.vision);
        map.put("metabolism", this.metabolism);
        map.put("generation", this.generation);
        return map;
    }

    /**
     * Returns a map containing all fields relevant to breeding indexed by their
     * names.
     * 
     * @return a map containing all fields relevant to breeding
     */
    public Map<String, Float> getBreedingStats() {
        Map<String, Float> map = new TreeMap<>();
        map.put("hungerIncrease", this.hungerIncrease);
        map.put("speed", this.speed);
        map.put("movementEnergy", this.movementEnergy);
        map.put("idleEnergy", this.idleEnergy);
        map.put("maxEnergy", this.maxEnergy);
        map.put("minEnergy", this.minEnergy);
        map.put("vision", this.vision);
        map.put("metabolism", this.metabolism);
        return map;
    }
}
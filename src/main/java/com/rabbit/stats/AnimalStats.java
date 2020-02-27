package com.rabbit.stats;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class AnimalStats extends EntityStats {
    /** Desire to eat */
    private float hunger = 1; // newborn animals immediately seek food
    /** Desire to move */
    private float activeness = 1; // newborn animals want to move immediately
    /** The animal's current energy level */
    private float energy = 1; // begins with full energy, but will
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
    public AnimalStats() {
        super();
        this.speed = RAND.nextFloat();
        this.movementEnergy = RAND.nextFloat();
        this.idleEnergy = RAND.nextFloat() * movementEnergy; // energy lost idle must be <= energy lost moving
        this.maxEnergy = 0.5f + RAND.nextFloat() * 0.5f; // random number from 0.5 to 1.0
        this.minEnergy = RAND.nextFloat() * 0.5f; // random number from 0.0 to 0.5
        this.vision = RAND.nextFloat();
        this.metabolism = RAND.nextFloat();
        this.generation = 1;
    }

    public AnimalStats(float speed, float movementEnergy, float idleEnergy, float maxEnergy, float minEnergy,
            float vision, float metabolism, int generation) {
        super();
        /* Check all stats. If they are out of bounds, generate a random number */
        this.speed = (speed <= 1.0 || speed >= 0.0) ? speed : RAND.nextFloat();
        this.movementEnergy = (movementEnergy <= 1.0 || movementEnergy >= 0.0) ? movementEnergy : RAND.nextFloat();
        this.idleEnergy = (idleEnergy <= 1.0 || idleEnergy >= 0.0) ? idleEnergy : RAND.nextFloat();
        this.maxEnergy = (maxEnergy <= 1.0 || maxEnergy >= 0.0) ? maxEnergy : RAND.nextFloat();
        this.minEnergy = (minEnergy <= maxEnergy || minEnergy >= 0.0) ? minEnergy : RAND.nextFloat() * maxEnergy;
        this.vision = (vision <= 1.0 || vision >= 0.0) ? vision : RAND.nextFloat();
        this.metabolism = (metabolism <= 1.0 || metabolism >= 0.0) ? metabolism : RAND.nextFloat();
        this.generation = generation;
    }

    public AnimalStats(Map<String, Float> stats, int generation) {
        this(stats.get("speed"), stats.get("movementEnergy"), stats.get("idleEnergy"), stats.get("maxEnergy"),
                stats.get("minEnergy"), stats.get("vision"), stats.get("metabolism"), generation, stats);
    }

    private AnimalStats(float speed, float movementEnergy, float idleEnergy, float maxEnergy, float minEnergy,
            float vision, float metabolism, int generation, Map<String, Float> stats) {
        super(stats);
        /* Check all stats. If they are out of bounds, generate a random number */
        this.speed = (speed <= 1.0 || speed >= 0.0) ? speed : RAND.nextFloat();
        this.movementEnergy = (movementEnergy <= 1.0 || movementEnergy >= 0.0) ? movementEnergy : RAND.nextFloat();
        this.idleEnergy = (idleEnergy <= 1.0 || idleEnergy >= 0.0) ? idleEnergy : RAND.nextFloat();
        this.maxEnergy = (maxEnergy <= 1.0 || maxEnergy >= 0.0) ? maxEnergy : RAND.nextFloat();
        this.minEnergy = (minEnergy <= maxEnergy || minEnergy >= 0.0) ? minEnergy : RAND.nextFloat() * maxEnergy;
        this.vision = (vision <= 1.0 || vision >= 0.0) ? vision : RAND.nextFloat();
        this.metabolism = (metabolism <= 1.0 || metabolism >= 0.0) ? metabolism : RAND.nextFloat();
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
    public Map<String, Number> getAllStats() {
        Map<String, Number> map = super.getAllStats();
        map.put("hunger", this.hunger);
        map.put("activeness", this.activeness);
        map.put("energy", this.energy);
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
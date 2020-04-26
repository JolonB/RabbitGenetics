package com.rabbit.stats;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.awt.Point;

public class AnimalStats extends EntityStats {
    /** Desire to eat */
    private float hunger = 1; // newborn animals immediately seek food
    /** Desire to move */
    private float activeness = 1; // newborn animals want to move immediately
    /** The animal's current energy level */
    private float energy = 1; // begins with full energy, but will immediately be set to maxEnergy
    /* Direction animal is facing (from -179 to +180; 0 is right) */
    private short orientation = (short) (-179 + RAND.nextInt(360));
    /* The maximum amount the animal can turn in any step */
    private final short maxRotation;
    /** The value at which the animal will solely seek food */
    private final float energyPanic;
    /** The level of importance of hunger in deciding the animal's actions */
    private final float hungerImportance;
    /** Rate of change of hunger per step */
    private final float hungerIncrease;
    /** Minimum activeness. Activeness will drop to this value after movement */
    private final float speed;
    /** Energy lost during movement */
    private final float movementEnergy;
    /** Energy lost while stationary */
    private final float idleEnergy;
    /** The range at which an animal can see another entity */
    private final short vision;
    /** The angle from either side of orientation that the animal can see */
    private final short fieldOfView;
    /** Food to energy conversion efficiency. Higher means more energy/food */
    private final float metabolism;
    /** The generation of the animal (longest line of ancestors + 1) */
    private final int generation;

    /**
     * Generate random stats for an animal
     */
    protected AnimalStats() {
        super();
        this.maxRotation = (short) RAND.nextInt(180); // never needs to be able to turn more than 180 degrees
        this.hungerImportance = randomFloat(0.0f, 1.0f);
        this.hungerIncrease = randomFloat(0.0f, 0.1f);
        this.speed = randomFloat(0.0f, 1.0f);
        this.movementEnergy = randomFloat(0.0f, 0.1f);
        this.idleEnergy = randomFloat(0.0f, movementEnergy); // energy lost idle must be <= energy lost moving
        this.energyPanic = randomFloat(movementEnergy, 1.0f);
        this.vision = (short) RAND.nextInt(20); // TODO maybe increase this
        this.fieldOfView = (short) RAND.nextInt(180);
        this.metabolism = randomFloat(0.0f, 1.0f);
        this.generation = 1;
    }

    protected AnimalStats(Map<String, Number> stats, int generation) {
        super(stats);
        this.maxRotation = (short) stats.get("maxRotation");
        this.hungerImportance = (short) stats.get("hungerImportance");
        this.hungerIncrease = (float) stats.get("hungerIncrease");
        this.speed = (float) stats.get("speed");
        this.movementEnergy = (float) stats.get("movementEnergy");
        this.idleEnergy = (float) stats.get("idleEnergy");
        this.energyPanic = (float) stats.get("energyPanic");
        this.vision = (short) stats.get("vision");
        this.fieldOfView = (short) stats.get("fieldOfView");
        this.metabolism = (float) stats.get("metabolism");
        this.generation = generation;
    }

    public int getMaxRotation() {
        return this.maxRotation;
    }

    public float getHunger() {
        return this.hunger;
    }

    public float getHungerImportance() {
        return this.hungerImportance;
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

    public float getEnergyPanic() {
        return this.energyPanic;
    }

    public short getVision() {
        return this.vision;
    }

    public short getFieldOfView() {
        return this.fieldOfView;
    }

    public float getMetabolism() {
        return this.metabolism;
    }

    public short getOrientation() {
        return this.orientation;
    }

    public void changeOrientation() {
        /* Change by some random value between -maxRotation and +maxRotation */
        changeOrientation(-this.maxRotation + RAND.nextInt(this.maxRotation * 2 + 1));
    }

    public void changeOrientationCW(int change) {
        changeOrientation(change);
    }

    public void changeOrientationCCW(int change) {
        changeOrientation(-change);
    }

    private void changeOrientation(int change) {
        this.orientation += change;
        checkOrientation();
    }

    public void checkOrientation() {
        /* Ensure orientation remains between +180 (incl.) and -180 (excl.) */
        while (this.orientation > 180) {
            this.orientation -= 180;
        }
        while (this.orientation <= -180) {
            this.orientation += 180;
        }
    }

    public Point getCellInFront() {
        double deltaX = Math.cos(Math.PI * this.orientation / 180.0);
        double deltaY = Math.sin(Math.PI * this.orientation / 180.0);
        return new Point(deltaX >= 0.5 ? 1 : (deltaX <= -0.5 ? -1 : 0), deltaY >= 0.5 ? 1 : (deltaY <= -0.5 ? -1 : 0));
    }

    public int getGeneration() {
        return this.generation;
    }

    public void increaseEnergy(float increaseBy) {
        updateEnergy(increaseBy);
        this.energy = Math.min(this.energy, 1.0f);
    }

    public void decreaseEnergy(float decreaseBy) {
        updateEnergy(-decreaseBy);
        this.energy = Math.max(this.energy, 0.0f);
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
        map.put("Energy", this.energy);
        map.put("Orientation", this.orientation);
        map.put("Generation", this.generation);
        map.put("vision", this.vision);
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
        map.put("maxRotation", this.maxRotation);
        map.put("hunger", this.hunger);
        map.put("activeness", this.activeness);
        map.put("energy", this.energy);
        map.put("hungerImportance", this.hungerImportance);
        map.put("hungerIncrease", this.hungerIncrease);
        map.put("speed", this.speed);
        map.put("movementEnergy", this.movementEnergy);
        map.put("idleEnergy", this.idleEnergy);
        map.put("energyPanic", this.energyPanic);
        map.put("vision", this.vision);
        map.put("fieldOfView", this.fieldOfView);
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
    public Map<String, Number> getBreedingStats() {
        Map<String, Number> map = new TreeMap<>();
        map.put("maxRotation", this.maxRotation);
        map.put("hungerImportance", this.hungerImportance);
        map.put("hungerIncrease", this.hungerIncrease);
        map.put("speed", this.speed);
        map.put("movementEnergy", this.movementEnergy);
        map.put("idleEnergy", this.idleEnergy);
        map.put("energyPanic", this.energyPanic);
        map.put("vision", this.vision);
        map.put("fieldOfView", this.fieldOfView);
        map.put("metabolism", this.metabolism);
        return map;
    }
}
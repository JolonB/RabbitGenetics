package com.rabbit.entities;

import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rabbit.stats.CabbageStats;
import com.rabbit.terrain.Terrain;

public class Cabbage extends Plant {
    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(Cabbage.class.getName());

    private static final String IMG_NAME = "cabbage.png";
    private static Image image = null;

    public Cabbage() {
        this(0, 0);
        LOGGER.warning("The constructor Cabbage() should only be used in tests");
    }

    public Cabbage(final int xPos, final int yPos) {
        this(xPos, yPos, new CabbageStats());
    }

    public Cabbage(final int xPos, final int yPos, final CabbageStats stats) {
        super(xPos, yPos, stats);
    }

    @Override
    public Image getImage(final int dim) {
        if (Cabbage.image == null) {
            Cabbage.image = getScaledImage(IMG_NAME, dim);
        }
        return Cabbage.image;
    }

    public Image getScaledImage(final int dim) {
        return super.getScaledImage(IMG_NAME, dim);
    }

    @Override
    public char toChar() {
        return 'c';
    }

    @Override
    public String toString() {
        return "Cabbage";
    }

    private CabbageStats getCabbageStats() {
        return (CabbageStats) this.getStats();
    }

    @Override
    public void updateStats() {
        super.updateStats();
    }

    @Override
    public Action calculateAction(final Terrain[][] terrain, final Entity[][] entities) {
        // TODO Maybe change this if actions could be made specific to cabbage
        return super.calculateAction(terrain);
    }
}

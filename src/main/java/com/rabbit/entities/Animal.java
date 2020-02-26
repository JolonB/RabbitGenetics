package com.rabbit.entities;

import java.util.logging.Logger;

public abstract class Animal extends Entity {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Animal.class.getName());

	/** Desire to eat */
	private double hunger;
	/** Desire to move */
	private double activeness;
	/** Minimum activeness */
	private double speed;
	/** Energy lost during movement */
	private double movementEnergy;
	/** Energy lost while stationary */
	private double idleEnergy;
	/** Energy level at which death occurs */
	private double minEnergy;
	/** The maximum energy before no more energy can be gained */
	private double maxEnergy;
	/** The range at which an animal can see another entity */
	private double vision;
	
	public Animal(final int xPos, final int yPos) {
		super(xPos, yPos);
	}
}

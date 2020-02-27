package com.rabbit.entities;

import java.util.logging.Logger;

import com.rabbit.stats.AnimalStats;

public abstract class Animal extends Entity {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Animal.class.getName());

	public Animal(final int xPos, final int yPos, AnimalStats stats) {
		super(xPos, yPos, stats);
	}
}

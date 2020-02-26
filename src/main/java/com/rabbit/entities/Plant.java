package com.rabbit.entities;

import java.util.logging.Logger;

public abstract class Plant extends Entity {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Plant.class.getName());

	public Plant(final int xPos, final int yPos) {
		super(xPos, yPos);
	}
}

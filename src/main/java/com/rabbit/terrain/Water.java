package com.rabbit.terrain;

import java.awt.Image;
import java.util.logging.Logger;

public class Water extends Terrain {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Water.class.getName());

	private static final String IMG_NAME = "water.png";
	private static Image image = null;

	@Override
	public char toChar() {
		return 'W';
	}

	@Override
	public String toString() {
		return "Water";
	}

	@Override
	public void draw(int xPos, int yPos) {
		// TODO implement
		throw new UnsupportedOperationException("draw not yet implemented");
	}

	@Override
	public Image getImage(int dim) {
		if (Water.image == null) {
			Water.image = getScaledImage(IMG_NAME, dim);
		}
		return Water.image;
	}
}

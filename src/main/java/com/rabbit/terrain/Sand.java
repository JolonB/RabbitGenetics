package com.rabbit.terrain;

import java.awt.Image;
import java.util.logging.Logger;

public class Sand extends Terrain {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Sand.class.getName());
	
	private static final String IMG_NAME = "img/sand.png";
	private static Image image = null;

	@Override
	public char toChar() {
		return 'S';
	}

	@Override
	public String toString() {
		return "Sand";
	}

	@Override
	public void draw(int xPos, int yPos) {
		// TODO implement
		throw new UnsupportedOperationException("draw not yet implemented");
	}

	@Override
	public Image getImage(int dim) {
		if (Sand.image == null) {
			Sand.image = getScaledImage(IMG_NAME, dim);
		}
		return Sand.image;
	}

}

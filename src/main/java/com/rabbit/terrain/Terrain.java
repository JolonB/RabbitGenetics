package com.rabbit.terrain;

import java.awt.Image;
import java.util.logging.Logger;

import com.rabbit.map_container.MapComponent;

public abstract class Terrain extends MapComponent {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Terrain.class.getName());

	public abstract void draw(int xPos, int yPos);

	public Terrain(final int xPos, final int yPos) {
		super(xPos, yPos);
	}

	@Override
	public String toString() {
		throw new UnsupportedOperationException("toString has not been implemented");
	}

	@Override
	public abstract char toChar();

	@Override
	public abstract Image getImage(int dim);
}

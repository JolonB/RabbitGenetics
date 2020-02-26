package com.rabbit.map_container;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public abstract class MapComponent {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(MapComponent.class.getName());

	/**
	 * Sets the image to the one provided by imgName. Returns the Image object.
	 * 
	 * @param imgName The filename and path of the image
	 * @param dim     The dimension of the image
	 * @return The image
	 */
	protected static Image getScaledImage(String imgName, int dim) {
		Image image = null;
		try {
			image = ImageIO.read(new File(imgName)).getScaledInstance(dim, dim, Image.SCALE_FAST);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Could not find file: " + imgName, e);
		}

		return image;
	}

	public abstract Image getImage(int dim);

	public Image getImage() {
		/* This should throw an exception if called at the wrong time */
		try {
			return getImage(0);
		} catch (IllegalArgumentException e) {
			if (LOGGER.isLoggable(Level.SEVERE)) {
				LOGGER.log(Level.SEVERE, "Image has not been set yet. It should be set in the constructor");
			}
		}
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		return this.getClass().equals(obj.getClass());
	}

	@Override
	/**
	 * TODO override this in child classes
	 */
	public int hashCode() {
		return this.toString().hashCode();
	}

	public abstract char toChar();

}

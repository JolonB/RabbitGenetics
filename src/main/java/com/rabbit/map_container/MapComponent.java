package com.rabbit.map_container;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public abstract class MapComponent {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(MapComponent.class.getName());

	private static final String FILE_PATH = "img/";

	private Point pos;

	public MapComponent(int xPos, int yPos) {
		this.pos = new Point(xPos, yPos);
	}

	public void setPos(final int xPos, final int yPos) {
		this.pos.x = xPos;
		this.pos.y = yPos;
	}

	public void setPos(final Point pos) {
		this.pos = pos;
	}

	public void setX(final int xPos) {
		this.pos.x = xPos;
	}

	public void setY(final int yPos) {
		this.pos.y = yPos;
	}

	public Point getPos() {
		return this.pos;
	}

	public int getX() {
		return this.pos.x;
	}

	public int getY() {
		return this.pos.y;
	}

	/**
	 * Sets the image to the one provided by imgName. Returns the Image object.
	 * 
	 * @param imgName The filename and path of the image
	 * @param dim     The dimension of the image
	 * @return The image
	 */
	protected static Image getScaledImage(String imgName, int dim) {
		Image image = null;
		imgName = FILE_PATH + imgName;
		try {
			image = ImageIO.read(new File(imgName)).getScaledInstance(dim, dim, Image.SCALE_FAST);
		} catch (IOException e) {
			if (LOGGER.isLoggable(Level.SEVERE)) {
				LOGGER.log(Level.SEVERE, "Could not find file: " + imgName, e);
			}
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

	public static List<Point> checkSurroundingCells(MapComponent comp, MapComponent[][] cells,
			Class<? extends MapComponent> cls) {

		try {
			return checkSurroundingCells(comp, cells, cls.newInstance().toChar());
		} catch (InstantiationException | IllegalAccessException e) {
			if (LOGGER.isLoggable(Level.SEVERE)) {
				LOGGER.log(Level.SEVERE, "Unable to create an instance of " + cls.getName());
			}
			e.printStackTrace();
		}
		return new ArrayList<Point>();
	}

	public static List<Point> checkSurroundingCells(MapComponent comp, MapComponent[][] cells, char chr) {
		List<Point> found = new ArrayList<>();
		Point currentCell = comp.getPos();
		for (int i = Math.max(0, currentCell.x - 1); i <= Math.min(cells.length - 1, currentCell.x + 1); i++) {
			for (int j = Math.max(0, currentCell.y - 1); j <= Math.min(cells[0].length - 1, currentCell.y + 1); j++) {
				if (i == currentCell.x && j == currentCell.y) {
					continue;
				}
				if (cells[i][j].toChar() == chr) {
					found.add(new Point(i, j));
				}
			}
		}
		return found;
	}

	public abstract char toChar();

}

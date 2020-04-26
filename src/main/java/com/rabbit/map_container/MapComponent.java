package com.rabbit.map_container;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.rabbit.entities.Rabbit;

public abstract class MapComponent {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(MapComponent.class.getName());
	protected static final Random RAND = new Random();

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

	public static double distanceTo(Point pnt1, Point pnt2) {
		return Math.hypot(pnt1.getX() - pnt2.getX(), pnt1.getY() - pnt2.getY());
	}

	public double distanceTo(Point other) {
		return distanceTo(this.getPos(), other);
	}

	public double distanceTo(MapComponent other) {
		return distanceTo(this.getPos(), other.getPos());
	}

	public static List<Point> findPointsOfType(List<Point> points, MapComponent[][] cells,
			Class<? extends MapComponent> cls) {
		List<Point> found = new ArrayList<>();
		for (Point pnt : points) {
			if (cls.isAssignableFrom(cells[pnt.x][pnt.y].getClass())) {
				found.add(pnt);
			}
		}
		return found;
	}

	public static List<MapComponent> convertPointsToCells(List<PointAngle> points, MapComponent[][] cells) {
		List<MapComponent> cellList = new ArrayList<>();
		for (Point pnt : points) {
			cellList.add(cells[pnt.x][pnt.y]);
		}
		return cellList;
	}

	public static List<PointAngle> removeOutOfBounds(List<PointAngle> points, Point center, MapComponent[][] cells) {
		List<PointAngle> inBounds = new ArrayList<>();
		for (PointAngle pnt : points) {
			PointAngle pntShifted = new PointAngle(pnt.x + center.x, pnt.y + center.y);
			/* Check that point is still in bounds even when shifted */
			if (pntShifted.x >= 0 && pntShifted.x < cells.length && pntShifted.y >= 0
					&& pntShifted.y < cells[0].length) {
				inBounds.add(pntShifted);
			}
		}
		return inBounds;
	}

	public static List<PointAngle> getCellsWithinRangeWithinToleranceInBounds(int range, int direction, int tolerance,
			Point center, MapComponent[][] cells) {
		return removeOutOfBounds(getCellsWithinRangeWithinTolerance(range, direction, tolerance), center, cells);
	}

	public static List<PointAngle> getCellsWithinRangeWithinTolerance(int range, int direction, int tolerance) {
		List<PointAngle> cells = new ArrayList<>();
		for (int distance = 1; distance <= range; distance++) {
			cells.addAll(getCellsAtDistanceWithinTolerance(distance, direction, tolerance));
		}
		return cells;
	}

	public static List<PointAngle> getCellsAtDistanceWithinTolerance(int distance, int direction, int tolerance) {
		PriorityQueue<PointAngle> orderedCells = queuePoints(getCellsAtDistanceGeneric(distance), direction);

		List<PointAngle> cells = new ArrayList<>();
		while (!orderedCells.isEmpty()) {
			PointAngle cell = orderedCells.poll();
			if (Math.abs(cell.getAngle()) > tolerance) {
				break;
			}
			cells.add(cell);
		}
		return cells;
	}

	public static List<PointAngle> getAngleCellsAtDistanceGeneric(int distance) {
		return getAngleCellsAtDistanceGeneric(distance, 0);
	}

	/**
	 * TODO fill this in
	 * 
	 * @param distance
	 * @param direction
	 * @return
	 */
	public static List<PointAngle> getAngleCellsAtDistanceGeneric(int distance, int direction) {
		return sortPointsByAngle(getCellsAtDistanceGeneric(distance), direction);
	}

	private static List<PointAngle> sortPointsByAngle(List<PointAngle> cells, int direction) {
		PriorityQueue<PointAngle> orderedCells = queuePoints(cells, direction);

		/* Convert PriorityQueue to List */
		cells.clear();
		while (!orderedCells.isEmpty()) {
			cells.add(orderedCells.poll());
		}

		return cells;
	}

	private static PriorityQueue<PointAngle> queuePoints(List<PointAngle> cells, int direction) {
		if (direction > 180) {
			direction = direction - 180;
		}

		PriorityQueue<PointAngle> orderedCells = new PriorityQueue<>();
		for (Point cell : cells) {
			orderedCells.add(new PointAngle(cell, direction));
		}
		return orderedCells;
	}

	public static List<PointAngle> getCellsAtDistanceGeneric(int distance) {
		List<PointAngle> cells = new ArrayList<>();
		for (int i = -distance; i <= distance; i++) {
			for (int j = -distance; j <= distance; j++) {
				/* Skip if not looking around the circumference */
				if (Math.abs(i) != distance && Math.abs(j) != distance) {
					continue;
				}
				cells.add(new PointAngle(i, j));
			}
		}
		return cells;
	}

	public static MapComponent getClosestComponent(MapComponent comp, MapComponent[][] cells, char chr, int range,
			short direction) {
		// iterate through cells beginning at the direction specified until all cells
		// within the range have been checked
		return null; // TODO return the closest component
	}

	@SafeVarargs
	public static List<Point> checkSurroundingCells(MapComponent comp, MapComponent[][] cells,
			Class<? extends MapComponent>... cls) {
		return checkSurroundingCells(comp, cells, 1, cls);
	}

	public static List<Point> checkSurroundingCells(MapComponent comp, MapComponent[][] cells, char... chr) {
		return checkSurroundingCells(comp, cells, 1, chr);
	}

	public static List<Point> checkSurroundingCells(int xPos, int yPos, MapComponent[][] cells, char... chr) {
		return checkSurroundingCells(xPos, yPos, cells, 1, chr);
	}

	@SafeVarargs
	public static List<Point> checkSurroundingCells(MapComponent comp, MapComponent[][] cells, int range,
			Class<? extends MapComponent>... classes) {

		char[] chars = new char[classes.length];
		for (int i = 0; i < classes.length; i++) {
			try {
				chars[i] = classes[i].getDeclaredConstructor(int.class, int.class).newInstance(0, 0).toChar();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				if (LOGGER.isLoggable(Level.SEVERE)) {
					LOGGER.log(Level.SEVERE, "Unable to create an instance of " + classes[i].getName());
				}
				e.printStackTrace();
				return new ArrayList<Point>();
			}
		}
		return checkSurroundingCells(comp, cells, range, chars);
	}

	public static List<Point> checkSurroundingCells(MapComponent comp, MapComponent[][] cells, int range, char... chr) {
		return checkSurroundingCells(comp.getX(), comp.getY(), cells, range, chr);
	}

	public static List<Point> checkSurroundingCells(int xPos, int yPos, MapComponent[][] cells, int range,
			char... chars) {
		List<Point> found = new ArrayList<>();
		String charStr = new String(chars);
		for (int i = Math.max(0, xPos - range); i <= Math.min(cells.length - 1, xPos + range); i++) {
			for (int j = Math.max(0, yPos - range); j <= Math.min(cells[0].length - 1, yPos + range); j++) {
				/* Skip if looking at itself */
				if (i == xPos && j == yPos) {
					continue;
				}
				if (charStr.indexOf(cells[i][j].toChar()) != -1) {
					found.add(new Point(i, j));
				}
			}
		}
		return found;
	}

	public abstract char toChar();

	public static class PointAngle extends Point implements Comparable<PointAngle> {
		/**
		 *
		 */
		private static final long serialVersionUID = -3861204728432946494L;
		/** The absolute angle with no consideration of the offset */
		private final int naturalAngle;
		/** The angle relative to some offset */
		private int angle;

		public PointAngle(int x, int y) {
			this(x, y, 0);
		}

		public PointAngle(Point pnt) {
			this(pnt, 0);
		}

		public PointAngle(Point pnt, int angleOffset) {
			this(pnt.x, pnt.y, angleOffset);
		}

		public PointAngle(int x, int y, int angleOffset) {
			super(x, y);
			this.naturalAngle = (int) (180 * Math.atan2(y, x) / Math.PI);
			changeOffset(angleOffset);
		}

		public int getAngle() {
			return this.angle;
		}

		public void changeOffset(int angleOffset) {
			int angle = this.naturalAngle;
			angle -= angleOffset;
			if (angle > 180) {
				angle = angle - 360;
			} else if (angle < -180) {
				angle = angle + 360;
			}
			this.angle = angle;
		}

		@Override
		public int compareTo(PointAngle other) {
			return Math.abs(this.getAngle()) - Math.abs(other.getAngle());
		}

	}

}

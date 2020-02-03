package entities;

import java.awt.Point;
import java.util.logging.Logger;

import map_container.MapComponent;
import terrain.Terrain;

public abstract class Entity extends MapComponent {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Entity.class.getName());

	public final Action act;
	private Point position;

	public Entity(int x, int y) {
		act = new Action(this);
		this.position = new Point(x, y);
	}

	public void setPos(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}

	public void setPos(Point pos) {
		this.position = pos;
	}

	public void setX(int x) {
		this.position.x = x;
	}

	public void setY(int y) {
		this.position.y = y;
	}

	public Point getPos() {
		return this.position;
	}

	public int getX() {
		return this.position.x;
	}

	public int getY() {
		return this.position.y;
	}

	public abstract Action calculateAction(Terrain[][] terrain);

}

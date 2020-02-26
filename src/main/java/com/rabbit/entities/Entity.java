package com.rabbit.entities;

import java.awt.Point;
import java.util.logging.Logger;

import com.rabbit.map_container.MapComponent;
import com.rabbit.terrain.Terrain;

public abstract class Entity extends MapComponent {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Entity.class.getName());

	private Point pos;

	public Entity(final int xPos, final int yPos) {
		super();
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

	public abstract Action calculateAction(final Terrain[][] terrain); // TODO do we need entities here too?
	
	public abstract boolean performAction(final Action action, Entity[][] entities);

}

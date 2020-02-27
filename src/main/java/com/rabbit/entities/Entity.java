package com.rabbit.entities;

import java.awt.Point;
import java.util.logging.Logger;

import com.rabbit.map_container.MapComponent;
import com.rabbit.stats.EntityStats;
import com.rabbit.terrain.Terrain;

public abstract class Entity extends MapComponent {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Entity.class.getName());

	private final EntityStats stats;
	private Point position;

	public Entity(final int xPos, final int yPos, EntityStats stats) {
		super();
		this.stats = stats;
		this.position = new Point(xPos, yPos);
	}

	public void setPos(final int xPos, final int yPos) {
		this.position.x = xPos;
		this.position.y = yPos;
	}

	public void setPos(final Point pos) {
		this.position = pos;
	}

	public void setX(final int xPos) {
		this.position.x = xPos;
	}

	public void setY(final int yPos) {
		this.position.y = yPos;
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

	public EntityStats getStats() {
		return this.stats;
	}

	public abstract Action calculateAction(final Terrain[][] terrain); // TODO do we need entities here too?

	public abstract boolean performAction(final Action action, Entity[][] entities);

}

package com.rabbit.entities;

import java.awt.Image;
import java.awt.Point;
import java.util.logging.Logger;

import com.rabbit.map_container.MapComponent;
import com.rabbit.stats.EntityStats;
import com.rabbit.terrain.Terrain;

public abstract class Entity extends MapComponent {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Entity.class.getName());

	private final EntityStats stats;
	private Point pos;

	protected Entity(final int xPos, final int yPos, EntityStats stats) {
		super();
		this.stats = stats;
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

	public EntityStats getStats() {
		return this.stats;
	}

	public void updateStats() {
		stats.incrementTimeAlive();
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj;
	}

	public abstract Action calculateAction(final Terrain[][] terrain); // TODO do we need entities here too?

	public abstract boolean doEat(final Action action, Entity[][] newEntities);

	public abstract boolean doBreed(final Action action, Entity[][] newEntities);

	public abstract boolean doMove(final Action action, Entity[][] newEntities);

	public abstract boolean doDie(final Action action, Entity[][] newEntities);

	public boolean doNothing(final Action action, Entity[][] newEntities) {
		newEntities[action.getX()][action.getY()] = this;
		return true;
	}

	public abstract Image getScaledImage(final int dimension);
}

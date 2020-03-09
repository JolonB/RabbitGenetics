package com.rabbit.entities;

import java.awt.Image;
import java.util.logging.Logger;

import com.rabbit.map_container.MapComponent;
import com.rabbit.stats.EntityStats;
import com.rabbit.terrain.Terrain;

public abstract class Entity extends MapComponent {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Entity.class.getName());

	private final EntityStats stats;
	private boolean alive = true;

	protected Entity(final int xPos, final int yPos, EntityStats stats) {
		super(xPos, yPos);
		this.stats = stats;
	}

	public EntityStats getStats() {
		return this.stats;
	}

	public void updateStats() {
		stats.incrementTimeAlive();
	}

	public boolean getAlive() {
		return this.alive;
	}

	public void kill() {
		this.alive = false;
	}

	/**
	 * Checks if the entity has met the conditions for death. Returns the number of
	 * ways that the entity died (although this information probably doesn't have
	 * much use and will be 1 in most cases).
	 * 
	 * @return The number of ways the entity died
	 */
	protected int checkDeath() {
		int deaths = 0;
		return deaths;
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj;
	}

	public abstract Action calculateAction(final Terrain[][] terrain, final Entity[][] entities); // TODO do we need
																									// entities here
																									// too?

	public abstract boolean doEat(final Action action, Entity[][] newEntities);

	public abstract boolean doBreed(final Action action, Entity[][] newEntities);

	public abstract boolean doMove(final Action action, Entity[][] newEntities);

	public boolean doDie(final Action action, Entity[][] newEntities) {
		kill();
		return !this.alive;
	}

	public boolean doNothing(final Action action, Entity[][] newEntities) {
		newEntities[action.getX()][action.getY()] = this;
		return true;
	}

	public abstract Image getScaledImage(final int dimension);
}

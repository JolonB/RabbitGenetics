package com.rabbit.entities;

import java.util.logging.Logger;

import com.rabbit.stats.AnimalStats;

public abstract class Animal extends Entity {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Animal.class.getName());

	protected Animal(final int xPos, final int yPos, AnimalStats stats) {
		super(xPos, yPos, stats);
	}

	private AnimalStats getAnimalStats() {
		return (AnimalStats) this.getStats();
	}

	public void updateStats() {
		super.updateStats();
		this.getAnimalStats().increaseHunger();
	}

	@Override
	public boolean doEat(Action action, Entity[][] newEntities) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doBreed(Action action, Entity[][] newEntities) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doDie(Action action, Entity[][] newEntities) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doMove(final Action action, Entity[][] entities) {
		entities[action.getX()][action.getY()] = this;
		this.setPos(action.getX(), action.getY());
		/* Decrease the energy by the amount required to move */
		AnimalStats stats = this.getAnimalStats();
		stats.decreaseEnergy(stats.getMovementEnergy());
		stats.decreaseActiveness();
		return true;
	}
}

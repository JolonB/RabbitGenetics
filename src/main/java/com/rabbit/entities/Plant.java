package com.rabbit.entities;

import java.util.logging.Logger;

import com.rabbit.entities.Action.Act;
import com.rabbit.stats.PlantStats;
import com.rabbit.terrain.Terrain;

public abstract class Plant extends Entity {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Plant.class.getName());

	public Plant(final int xPos, final int yPos, final PlantStats stats) {
		super(xPos, yPos, stats);
	}

	private PlantStats getPlantStats() {
		return (PlantStats) this.getStats();
	}

	public Action calculateAction(final Terrain[][] terrain) {
		/* Die if it should die */
		if (this.getStats().getTimeAlive() > this.getPlantStats().getLifespan()) {
			return new Action(this, null, Act.DIE, this.getX(), this.getY());
		} 
		else {
			return new Action(this, null, Act.NOTHING, this.getX(), this.getY());
		}
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
		return super.doDie(action, newEntities);
	}

	@Override
	public boolean doMove(Action action, Entity[][] newEntities) {
		throw new IllegalArgumentException("Cannot call doMove on a plant");
	}
}

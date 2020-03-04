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
		final Action act;
		System.out.println(this.getStats().getAllStats());
		System.out.println(this.getStats().getTimeAlive() + " > " + this.getPlantStats().getLifespan() + "?");
		if (this.getStats().getTimeAlive() > this.getPlantStats().getLifespan()) {
			System.out.println("time to die");
			act = new Action(this, Act.DIE, this.getX(), this.getY());
		} else {
			act = new Action(this, Act.NOTHING, this.getX(), this.getY());
		}
		return act;
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
	public boolean doMove(Action action, Entity[][] newEntities) {
		throw new IllegalArgumentException("Cannot call doMove on a plant");
	}
}

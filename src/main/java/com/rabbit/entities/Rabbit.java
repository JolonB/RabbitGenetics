package com.rabbit.entities;

import java.awt.Image;
import java.awt.Point;
import java.util.List;
import java.util.logging.Logger;

import com.rabbit.entities.Action.Act;
import com.rabbit.map_container.MapComponent;
import com.rabbit.stats.RabbitStats;
import com.rabbit.terrain.Terrain;
import com.rabbit.terrain.Water;

public class Rabbit extends Animal {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Rabbit.class.getName());

	private static final String IMG_NAME = "rabbit.png";
	private static Image image = null;

	public Rabbit() {
		this(0, 0);
		LOGGER.warning("The constructor Rabbit() should only be used in tests");
	}

	public Rabbit(final int xPos, final int yPos) {
		this(xPos, yPos, new RabbitStats());
	}

	public Rabbit(final int xPos, final int yPos, RabbitStats stats) {
		super(xPos, yPos, stats);
	}

	@Override
	public Image getImage(final int dim) {
		if (Rabbit.image == null) {
			Rabbit.image = getScaledImage(IMG_NAME, dim);
		}
		return Rabbit.image;
	}

	public Image getScaledImage(final int dim) {
		return super.getScaledImage(IMG_NAME, dim);
	}

	@Override
	public char toChar() {
		return 'r';
	}

	@Override
	public String toString() {
		return "Rabbit";
	}

	private RabbitStats getRabbitStats() {
		return (RabbitStats) this.getStats();
	}

	@Override
	public void updateStats() {
		super.updateStats();
		this.getRabbitStats().increaseLibido();
	}

	private Action selectAdjacent(final Entity[][] entities) {
		RabbitStats stats = getRabbitStats();
		List<Point> adjacentCabbage = checkSurroundingCells(this, entities, Cabbage.class);
		List<Point> adjacentRabbit = checkSurroundingCells(this, entities, Rabbit.class);

		if (adjacentCabbage.size() == 0 && adjacentRabbit.size() == 0) {
			return null;
		}

		/* Use these variables to make this easier to extend */
		boolean shouldEat = false;
		boolean shouldBreed = false;

		if (adjacentCabbage.size() > 0 && adjacentRabbit.size() == 0 || stats.getEnergy() < stats.getEnergyPanic()) {
			shouldEat = true;
		} else if (adjacentRabbit.size() > 0 && stats.canBreed()) {
			shouldBreed = true;
		}

		if (shouldEat) {
			Point cabbagePos = adjacentCabbage.get(RAND.nextInt(adjacentCabbage.size()));
			return new Action(this, (Cabbage) entities[cabbagePos.x][cabbagePos.y], Act.EAT, this.getX(), this.getY());
		}
		if (shouldBreed) {
			for (Point pnt : adjacentRabbit) {
				Rabbit potentialMate = (Rabbit) entities[pnt.x][pnt.y];
				if (potentialMate.getRabbitStats().canBreed()) {
					return new Action(this, potentialMate, Act.BREED, this.getX(), this.getY());
				}
			}
		}
		/* If no action has been returned yet, just eat */ // TODO is there a better way to write this?
		Point cabbagePos = adjacentCabbage.get(RAND.nextInt(adjacentCabbage.size()));
		return new Action(this, (Cabbage) entities[cabbagePos.x][cabbagePos.y], Act.EAT, this.getX(), this.getY());
	}

	private Action selectNearby(Terrain[][] terrain, Entity[][] entities) {
		RabbitStats stats = getRabbitStats();
		/* Check if rabbit should target something */
		List<Point> viewRegion = MapComponent.getCellsWithinRangeWithinToleranceInBounds(stats.getVision(),
				stats.getOrientation(), stats.getFieldOfView(), this.getPos(), entities);
		Point closestRabbit = null;
		Point closestPlant = null;
		Point closestWater = null;
		for (Point pnt : viewRegion) {
			if (closestRabbit == null && entities[pnt.x][pnt.y] instanceof Rabbit) {
				/* Find the closest breedable rabbit */
				if (((Rabbit) entities[pnt.x][pnt.y]).getRabbitStats().canBreed()) {
					closestRabbit = pnt;
				}
			}
			if (closestPlant == null && entities[pnt.x][pnt.y] instanceof Plant) {
				closestPlant = pnt;
			}
			if (closestWater == null && terrain[pnt.x][pnt.y] instanceof Water) {
				closestWater = pnt;
			}
		}

		if (stats.getWaterAversion(distanceTo(closestWater))) {
			// get angle to water
			// if positive, decrease orientation
			// if negative, increase orientation
			// calculate next movement away from water
			// TODO try to move away from closest water source
		}

		if (stats.getHunger() * stats.getHungerImportance() >= stats.getLibido() * stats.getLibidoImportance()
				* (stats.canBreed() ? 1 : 0)) {
			// TODO track food
		} else {
			// TODO track mate
		}

		// TODO decide to target cabbage or rabbit, depending on energy, libido, hunger
		// TODO perhaps check if water is in front of it
		return null;
	}

	@Override
	public Action calculateAction(final Terrain[][] terrain, final Entity[][] entities) {
		RabbitStats stats = getRabbitStats();
		// TODO improve action taking
		/* Check if rabbit should die */
		if (!getAlive() || checkDeath() > 0) {
			return new Action(this, null, Act.DIE, this.getX(), this.getY());
		}
		/* Check what rabbit should do with adjacent cells */
		Action adjacentAction = selectAdjacent(entities);
		if (adjacentAction != null) {
			return adjacentAction;
		}
		/* Check what rabbit should do with nearby cells */
		Action nearbyAction = selectNearby(terrain, entities);
		if (nearbyAction != null) {
			return nearbyAction;
		}

		// TODO remove this bit. this is just straight line motion
		Point nextPos = this.getRabbitStats().getCellInFront();
		final Action act = new Action(this, null, Act.MOVE, this.getX() + nextPos.x, this.getY() + nextPos.y);
		return act;
	}
}

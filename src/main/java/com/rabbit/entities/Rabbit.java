package com.rabbit.entities;

import java.awt.Image;
import java.util.logging.Logger;

import com.rabbit.entities.Action.Act;
import com.rabbit.stats.RabbitStats;
import com.rabbit.terrain.Terrain;

public class Rabbit extends Animal {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Rabbit.class.getName());

	private static final String IMG_NAME = "img/rabbit.png";
	private static Image image = null;

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

	@Override
	public char toChar() {
		return 'r';
	}

	@Override
	public String toString() {
		return "Rabbit";
	}

	@Override
	public Action calculateAction(final Terrain[][] terrain) {
		// TODO improve action taking
		final Action act = new Action(this, Act.MOVE, this.getX() + 1, this.getY() + 1);
		return act;
	}

	@Override
	public boolean performAction(final Action action, Entity[][] entities) {
		switch (action.getAction()) {
			case MOVE:
				this.setPos(action.getX(), action.getY());
				entities[action.getX()][action.getY()] = this;
				return true;
			case BREED:
				break;
			case EAT:
				break;
			case DIE:
				break;
			case NOTHING:
				return true;
		}

		return false;
	}

}

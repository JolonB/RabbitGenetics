package com.rabbit.entities;

import java.awt.Image;
import java.util.logging.Logger;

import com.rabbit.entities.Action.Act;
import com.rabbit.terrain.Terrain;

public class Rabbit extends Animal {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Rabbit.class.getName());

	private static final String IMG_NAME = "img/rabbit.png";
	private static Image image = null;

	/** Energy lost during breeding */
	double breedingEnergy;
	/** Desire to reproduce */
	double horniness;

	public Rabbit(int x, int y) {
		super(x, y);
	}

	@Override
	public Image getImage(int dim) {
		if (Rabbit.image == null) {
			Rabbit.image = setImage(IMG_NAME, dim);
		}
		return Rabbit.image;
	}

	@Override
	public char toChar() {
		return 'r';
	}

	public String toString() {
		return "Rabbit";
	}

	@Override
	public Action calculateAction(Terrain[][] terrain) {
		// TODO Auto-generated method stub
		act.nextAction = Act.MOVE;
		act.x = this.getX() + 1;
		act.y = this.getY() + 1;

		return act;
	}

	@Override
	public boolean performAction(Action action, Entity[][] entities) {
		switch (action.nextAction) {
		case MOVE:
			this.setPos(action.x, action.y);
			entities[action.x][action.y] = this;
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

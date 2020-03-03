package com.rabbit.entities;

import java.awt.Image;
import java.util.logging.Logger;

import com.rabbit.entities.Action.Act;
import com.rabbit.stats.NullStats;
import com.rabbit.terrain.Terrain;

public class Null extends Entity {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Null.class.getName());

	private static final String IMG_NAME = "img/null.png";
	private static Image image = null;

	public Null() {
		super(0, 0, new NullStats());
	}

	@Override
	public Image getImage(final int dim) {
		if (Null.image == null) {
			Null.image = getScaledImage(IMG_NAME, dim);
		}
		return Null.image;
	}

	@Override
	public char toChar() {
		return 'n';
	}

	@Override
	public String toString() {
		return "Null";
	}

	@Override
	public Action calculateAction(final Terrain[][] terrain) {
		// TODO Auto-generated method stub
		return new Action(this, Act.NOTHING, this.getX(), this.getY());
	}

	@Override
	public boolean doMove(final Action action, final Entity[][] entities) {
		/* Do nothing. This should never be called */
		return true;
	}

	@Override
	public Image getScaledImage(int dimension) {
		return getScaledImage(IMG_NAME, dimension);
	}

}

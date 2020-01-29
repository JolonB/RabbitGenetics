package entities;

import java.awt.Image;
import java.util.logging.Logger;

import entities.Action.Act;
import terrain.Terrain;

public class Rabbit extends Animal {
	public Rabbit(int x, int y) {
		super(x, y);
	}

	private static final Logger LOGGER = Logger.getLogger(Rabbit.class.getName());

	private static final String IMG_NAME = "img/rabbit.png";
	private static Image image = null;

	/** Energy lost during breeding */
	double breedingEnergy;
	/** Desire to reproduce */
	double horniness;

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

	@Override
	public Action calculateAction(Terrain[][] terrain) {
		// TODO Auto-generated method stub
		act.nextAction = Act.MOVE;
		act.x = position.x + 1;
		act.y = position.y + 1;

		return act;
	}

}

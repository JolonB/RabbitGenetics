package entities;

import java.util.Random;
import java.util.logging.Logger;

public abstract class Animal extends Entity {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Animal.class.getName());

	Random rand = new Random();

	/* Direction animal is facing (0 is up) */
	short orientation;
	/** Desire to eat */
	double hunger;
	/** Desire to move */
	double activeness;
	/** Minimum activeness */
	double speed;
	/** Energy lost during movement */
	double movementEnergy;
	/** Energy lost while stationary */
	double idleEnergy;
	/** Energy level at which death occurs */
	double minEnergy;
	/** The maximum energy before no more energy can be gained */
	double maxEnergy;
	/** The range at which an animal can see another entity */
	double vision;

	public Animal(int x, int y) {
		super(x, y);
		/* Select random values for fields */
		setRandomFields();
	}

	private void setRandomFields() {
		orientation = (short) rand.nextInt(360);
		// TODO add more
	}
}

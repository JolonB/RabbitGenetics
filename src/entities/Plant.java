package entities;

import java.util.logging.Logger;

public abstract class Plant extends Entity {
	public Plant(int x, int y) {
		super(x, y);
	}

	private static final Logger LOGGER = Logger.getLogger(Plant.class.getName());

}

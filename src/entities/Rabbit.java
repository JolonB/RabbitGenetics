package entities;

import java.awt.Image;

import terrain.Water;

public class Rabbit extends Animal {
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
		// TODO Auto-generated method stub
		return 'r';
	}

}

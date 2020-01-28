package entities;

import java.awt.Image;

public class Null extends Entity {
	private static final String IMG_NAME = "img/null.png";
	private static Image image = null;

	@Override
	public Image getImage(int dim) {
		if (Null.image == null) {
			Null.image = setImage(IMG_NAME, dim);
		}
		return Null.image;
	}

	@Override
	public char toChar() {
		// TODO Auto-generated method stub
		return 'n';
	}

	public String toString() {
		return "Null";
	}

}

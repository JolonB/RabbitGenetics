package terrain;

import java.awt.Image;

public class Water extends Terrain {
	private static final String IMG_NAME = "img/water.png";
	private static Image image = null;

	@Override
	public char toChar() {
		return 'W';
	}

	@Override
	public String toString() {
		return "Water";
	}

	@Override
	public void draw(int x, int y) {
		// TODO implement
		throw new UnsupportedOperationException("draw not yet implemented");
	}

	@Override
	public Image getImage(int dim) {
		if (Water.image == null) {
			Water.image = setImage(IMG_NAME, dim);
		}
		return Water.image;
	}
}

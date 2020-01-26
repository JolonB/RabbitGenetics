package map;

import java.awt.Image;

public class Grass extends Terrain {
	private static final String IMG_NAME = "img/grass.png";
	private static Image image = null;

	@Override
	public char toChar() {
		return 'G';
	}

	@Override
	public String toString() {
		return "Grass";
	}

	@Override
	public void draw(int x, int y) {
		// TODO implement
		throw new UnsupportedOperationException("draw not yet implemented");
	}

	@Override
	public Image getImage(int dim) {
		if (Grass.image == null) {
			Grass.image = setImage(IMG_NAME, dim);
		}
		return Grass.image;
	}
}

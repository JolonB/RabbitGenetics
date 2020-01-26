package map;

import java.awt.Image;

public class Sand extends Terrain {
	private static final String IMG_NAME = "img/sand.png";
	private static Image image = null;

	@Override
	public char toChar() {
		return 'S';
	}

	@Override
	public String toString() {
		return "Sand";
	}

	@Override
	public void draw(int x, int y) {
		// TODO implement
		throw new UnsupportedOperationException("draw not yet implemented");
	}

	@Override
	public Image getImage(int dim) {
		if (Sand.image == null) {
			Sand.image = setImage(IMG_NAME, dim);
		}
		return Sand.image;
	}

}

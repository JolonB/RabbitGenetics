package terrain;

import java.awt.Image;
import java.util.logging.Logger;

public class Grass extends Terrain {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Grass.class.getName());
	
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

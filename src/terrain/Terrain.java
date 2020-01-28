package terrain;

import java.awt.Image;
import java.util.logging.Logger;

import map_container.MapComponent;

public abstract class Terrain extends MapComponent {
	private static final Logger LOGGER = Logger.getLogger(Terrain.class.getName());

	public abstract void draw(int x, int y);

	public String toString() {
		throw new UnsupportedOperationException("toString has not been implemented");
	}

	public abstract char toChar();

	public abstract Image getImage(int dim);
}

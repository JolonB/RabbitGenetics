package terrain;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import map_container.MapComponent;

public abstract class Terrain extends MapComponent {

	public abstract void draw(int x, int y);

	public String toString() {
		throw new UnsupportedOperationException("toString has not been implemented");
	}

	public abstract char toChar();

	public abstract Image getImage(int dim);
}

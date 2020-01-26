package map;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Terrain {

	/**
	 * Sets the image to the one provided by imgName. Returns the Image object.
	 * 
	 * @param imgName The filename and path of the image
	 * @param dim     The dimension of the image
	 * @return The image
	 */
	public static Image setImage(String imgName, int dim) {
		Image image = null;
		try {
			image = ImageIO.read(new File(imgName)).getScaledInstance(dim, dim, Image.SCALE_FAST);
		} catch (IOException e) {
			System.err.println("Could not find file: " + imgName);
			e.printStackTrace();
		}

		return image;
	}

	public abstract void draw(int x, int y);

	public String toString() {
		throw new UnsupportedOperationException("toString has not been implemented");
	}

	public abstract char toChar();

	public abstract Image getImage(int dim);
}

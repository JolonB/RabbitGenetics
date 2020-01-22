package map;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Grass implements Terrain {
	private static final String IMG_NAME = "img/grass.png";
	private static Image image = null;

	public Grass() {
		setImage();
	}
	
	public void setImage() {
		if (Grass.image == null) {
			try {
				Grass.image = ImageIO.read(new File(IMG_NAME)).getScaledInstance(15, 15, Image.SCALE_FAST);
			} catch (IOException e) {
				System.err.println("Could not find file: " + IMG_NAME);
				e.printStackTrace();
			}
		}
	}

	public char toChar() {
		return 'G';
	}

	public String toString() {
		return "Grass";
	}

	@Override
	public void draw(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getImage() {
		return Grass.image;
	}
}

package map;

import java.awt.Image;

public interface Terrain {
	
	public void setImage();

	public void draw(int x, int y);

	public String toString();

	public char toChar();

	public Image getImage();
}

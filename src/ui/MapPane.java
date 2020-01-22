package ui;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import map.Map;
import map.Terrain;

public class MapPane extends JPanel {
	Map map;

	public MapPane() {
		System.out.println("here");
		this.setLayout(new GridLayout(10, 10));
		try {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					try {
						Image img = ImageIO.read(new File("img/sample.png")).getScaledInstance(25, 25,
								Image.SCALE_FAST);
						JLabel imgLabel = new JLabel(new ImageIcon(img));
						this.add(imgLabel);
						System.out.println("add " + i + " " + j);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			BufferedImage img1 = ImageIO.read(new File("img/sample.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MapPane(Terrain[][] terrain) {
		this.map = new Map(terrain);
		int rows = terrain.length;
		int cols = terrain[0].length;
		this.setLayout(new GridLayout(rows, cols));

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Image img = terrain[i][j].getImage();
				JLabel imgLabel = new JLabel(new ImageIcon(img));
				this.add(imgLabel);
			}
		}
	}
}

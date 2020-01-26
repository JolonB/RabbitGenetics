package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import map.Map;
import map.Terrain;

public class MapPane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4987675307324245239L;
	Map map;

	public MapPane(Map map, Dimension dim) {
		this.map = map;
		Terrain[][] terrain = map.getTerrainImmutable();
		int rows = terrain.length;
		int cols = terrain[0].length;
		int scale = Math.min(dim.height / rows, dim.width / cols);
		GridLayout gLayout = new GridLayout(rows, cols);
		gLayout.setHgap(0);
		gLayout.setVgap(0);
		this.setLayout(gLayout);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Image img = terrain[i][j].getImage(scale);
				JLabel imgLabel = new JLabel(new ImageIcon(img));
				this.add(imgLabel);
			}
		}

		this.setSize(scale * cols, scale * rows);
		System.out.println(this.getSize().toString()); // TODO figure out how to remove weird gaps
	}
}

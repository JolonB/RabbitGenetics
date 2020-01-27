package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
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
		GridBagLayout gLayout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.fill = GridBagConstraints.BOTH;
		this.setLayout(gLayout);

		for (int i = 0; i < rows; i++) {
			gbc.gridy = i;
			for (int j = 0; j < cols; j++) {
				gbc.gridx = j;
				Image img = terrain[i][j].getImage(scale);
				JLabel imgLabel = new JLabel(new ImageIcon(img));
				this.add(imgLabel, gbc);
			}
		}

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	}
}

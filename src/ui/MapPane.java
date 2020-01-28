package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import map_container.MapComponent;
import map_container.MapContainer;

public class MapPane extends JPanel {

	private static final long serialVersionUID = 4987675307324245239L;
	MapContainer map;

	public MapPane(MapContainer map, Dimension dim) {
		this.map = map;
		MapComponent[][] contents = map.getContents();
		int rows = contents.length;
		int cols = contents[0].length;
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
				Image img = contents[i][j].getImage(scale);
				JLabel imgLabel = new JLabel(new ImageIcon(img));
				this.add(imgLabel, gbc);
			}
		}

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	}
}

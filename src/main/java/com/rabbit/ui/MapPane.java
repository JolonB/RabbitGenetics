package com.rabbit.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.rabbit.map_container.MapComponent;
import com.rabbit.map_container.MapContainer;

public class MapPane extends JPanel {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(MapPane.class.getName());

	private static final long serialVersionUID = 4987675307324245239L;
	private transient final MapContainer<?> map;

	public MapPane(MapContainer<? extends MapComponent> map, Dimension dim) {
		super();
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

	public Dimension getDimensions() {
		return this.map.getDimensions();
	}

	public MapComponent[][] getMapContents() {
		return this.map.getContentsImmutable();
	}
}

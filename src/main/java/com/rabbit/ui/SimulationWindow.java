package com.rabbit.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import java.awt.Component;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class SimulationWindow extends JLayeredPane {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(SimulationWindow.class.getName());

	private static final long serialVersionUID = 6478558435237472529L;

	public SimulationWindow(Dimension dim, MapPane background, MapPane foreground) {
		background.setBounds(0, 0, dim.width, dim.height);
		background.setOpaque(false);
		this.add(background, 0);

		foreground.setBounds(0, 0, dim.width, dim.height);
		foreground.setOpaque(false);
		this.add(foreground, 1); // add foreground in front of background
	}

	public SimulationWindow(Dimension dim, JPanel... panels) {
		if (panels.length == 0) {
			throw new IllegalArgumentException("Not enough arguments in Simulation Window");
		}

		for (int i = 0; i < panels.length; i++) {
			panels[i].setBounds(0, 0, dim.width, dim.height);
			panels[i].setOpaque(false);
			this.add(panels[i], Integer.valueOf(i));
		}

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	}

	private void redraw() {
		this.validate();
		this.repaint();
	}

	public void updateLayer(Dimension dim, MapPane layer, int index) {
		if (this.getComponentCount() - 1 < index) {
			throw new ArrayIndexOutOfBoundsException("Cannot remove layer that is out of bounds");
		}

		layer.setBounds(0, 0, dim.width, dim.height);
		layer.setOpaque(false);
		/* Remove old layer and add new layer */
		this.remove(index);
		this.add(layer, index);
		this.redraw(); // Not sure if this is really necessary
	}

	@Override
	public Component add(Component comp, int index) {
		return super.add(comp, this.getComponentCount() - index);
	}

	@Override
	public void remove(int index) {
		super.remove(this.getComponentCount() - 1 - index);
	}
}

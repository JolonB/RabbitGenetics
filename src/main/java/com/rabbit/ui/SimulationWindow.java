package com.rabbit.ui;

import java.awt.Dimension;
import java.util.logging.Logger;

import java.awt.Component;
import javax.swing.JLayeredPane;

public class SimulationWindow extends JLayeredPane {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(SimulationWindow.class.getName());

	private static final long serialVersionUID = 6478558435237472529L;

	public SimulationWindow(Dimension dim, MapPane background, MapPane foreground) {
		super();
		background.setBounds(0, 0, dim.width, dim.height);
		background.setOpaque(false);
		this.add(background, 0);

		foreground.setBounds(0, 0, dim.width, dim.height);
		foreground.setOpaque(false);
		this.add(foreground, 1); // add foreground in front of background

		this.setPreferredSize(dim);
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
	public final Component add(Component comp, int index) {
		return super.add(comp, this.getComponentCount() - index);
	}

	@Override
	public final void remove(int index) {
		super.remove(this.getComponentCount() - 1 - index);
	}
}

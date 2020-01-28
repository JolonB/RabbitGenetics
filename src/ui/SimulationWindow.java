package ui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class SimulationWindow extends JLayeredPane {
	public SimulationWindow(Dimension dim, JPanel... panels) {
		if (panels.length == 0) {
			throw new IllegalArgumentException("Not enough arguments in Simulation Window");
		}

		for (int i = 0; i < panels.length; i++) {
			panels[i].setBounds(0, 0, dim.width, dim.height);
			panels[i].setOpaque(false);
			this.add(panels[i], new Integer(i));
		}

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	}
}

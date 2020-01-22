package ui;

import java.awt.BorderLayout;
import javax.swing.*;

import map.Map;

public class Window {
	public Window() {
		createAndShowGUI();
	}

	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("HelloWorldSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel map = new MapPane(Map.generateSimplexMap(50, 50, -0.3));
		frame.getContentPane().add(map, BorderLayout.CENTER);
		JPanel controls = new ControlPane();
		frame.getContentPane().add(controls, BorderLayout.LINE_END);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

}

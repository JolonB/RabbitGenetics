package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import map.Map;

public class Window {
	public static final int WINDOW_HEIGHT = 1000;
	public static final int MAP_WIDTH = 1000;
	public static final int CONTROL_WIDTH = 200;

	public Window(int rows, int cols, int percentWater) {
		createAndShowGUI(rows, cols, percentWater);
	}

	private static void createAndShowGUI(int rows, int cols, int percentWater) {
		/* Create and set up the window */
		JFrame frame = new JFrame("Rabbit Breeding");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(MAP_WIDTH + CONTROL_WIDTH, WINDOW_HEIGHT);

		/* Set up the map and the controls */
		JPanel map = new MapPane(new Map(Map.generateSimplexMap(rows, cols, percentWater)),
				new Dimension(MAP_WIDTH, WINDOW_HEIGHT));
		frame.getContentPane().add(map, BorderLayout.CENTER);
		JPanel controls = new ControlPane();
		frame.getContentPane().add(controls, BorderLayout.LINE_END);

		/* Display the window */
		frame.setVisible(true);
	}
}

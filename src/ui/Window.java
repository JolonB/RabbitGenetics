package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.logging.Logger;

import javax.swing.*;

import map_container.EntityMap;
import map_container.MapComponent;
import map_container.TerrainMap;
import wrapper.NumberWrapper;

public class Window {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Window.class.getName());

	public static final int WINDOW_HEIGHT = 1000;
	public static final int MAP_WIDTH = 1000;
	public static final int CONTROL_WIDTH = 200;

	public Window(TerrainMap terrain, EntityMap entities, NumberWrapper timer) {
		checkMaps(terrain, entities);
		createAndShowGUI(terrain, entities, timer);
	}

	private static void createAndShowGUI(TerrainMap terrain, EntityMap entities, NumberWrapper timer) {
		/* Create and set up the window */
		JFrame frame = new JFrame("Rabbit Breeding");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(MAP_WIDTH + CONTROL_WIDTH, WINDOW_HEIGHT);

		/* Set up the map and the controls */
		JPanel background = new MapPane(terrain, new Dimension(MAP_WIDTH, WINDOW_HEIGHT));
		JPanel foreground = new MapPane(entities, new Dimension(MAP_WIDTH, WINDOW_HEIGHT));
		SimulationWindow simWindow = new SimulationWindow(new Dimension(MAP_WIDTH, WINDOW_HEIGHT), background,
				foreground);
		frame.getContentPane().add(simWindow, BorderLayout.CENTER);
		JPanel controls = new ControlPane(timer);
		frame.getContentPane().add(controls, BorderLayout.LINE_END);

		/* Display the window */
		frame.setVisible(true);
	}

	private static void checkMaps(TerrainMap terrain, EntityMap entities) {
		MapComponent[][] terrainArray = terrain.getContents();
		MapComponent[][] entitiesArray = entities.getContents();
		if (terrainArray.length != entitiesArray.length) {
			throw new ArrayIndexOutOfBoundsException("Both arrays must have the same number of rows");
		}
		for (int i = 0; i < terrainArray.length; i++) {
			if (entitiesArray[i].length != terrainArray[i].length) {
				throw new ArrayIndexOutOfBoundsException("Both arrays must have the same number of columns");
			}
		}
	}
}

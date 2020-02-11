package com.rabbit.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.logging.Logger;

import javax.swing.*;

import com.rabbit.map_container.EntityMap;
import com.rabbit.map_container.MapComponent;
import com.rabbit.wrapper.NumberWrapper;
import com.rabbit.map_container.TerrainMap;

public class Window {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Window.class.getName());

	public static final int WINDOW_HEIGHT = 1000;
	public static final int MAP_WIDTH = 1000;
	public static final int CONTROL_WIDTH = 200;
	public static MapPane foreground1, foreground2; // TODO fix the mix of static and non-static methods/fields

	private static boolean foreground1Visible = true;

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
		Dimension dim = new Dimension(MAP_WIDTH, WINDOW_HEIGHT);
		int rows = entities.getContents().length;
		int cols = entities.getContents()[0].length;
		JPanel background = new MapPane(terrain, dim);
		foreground1 = new MapPane(entities, dim);
		foreground2 = new MapPane(new EntityMap(EntityMap.generateEmptyEntityMap(rows, cols)), dim);
		SimulationWindow simWindow = new SimulationWindow(new Dimension(MAP_WIDTH, WINDOW_HEIGHT), background,
				foreground1, foreground2);
		foreground2.setVisible(false);
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

	public static void updateEntities(EntityMap entities) {
		/* Update the currently inactive MapPane */
		if (foreground1Visible) {
			foreground2.updateContents(entities);
		} else {
			foreground1.updateContents(entities);
		}
		foreground1Visible = !foreground1Visible;
		foreground1.setVisible(foreground1Visible);
		foreground2.setVisible(!foreground1Visible);
	}
}

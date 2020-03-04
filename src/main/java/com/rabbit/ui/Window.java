package com.rabbit.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseListener;

import java.util.logging.Logger;

import javax.swing.*;

import com.rabbit.map_container.EntityMap;
import com.rabbit.map_container.MapComponent;
import com.rabbit.map_container.MapContainer;
import com.rabbit.map_container.TerrainMap;
import com.rabbit.wrapper.BooleanWrapper;
import com.rabbit.wrapper.NumberWrapper;

public class Window {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Window.class.getName());

	public static final int WINDOW_HEIGHT = 1000;
	public static final int MAP_WIDTH = 1000;
	public static final int CONTROL_WIDTH = 200;
	private static final Dimension DIM = new Dimension(MAP_WIDTH, WINDOW_HEIGHT);
	private final MouseListener mouseListener;
	private final SimulationWindow simWindow; // TODO fix the mix of static and non-static methods/fields
	private final InfoWindow info;
	private final NumberWrapper timer;
	private final BooleanWrapper running;

	public Window(TerrainMap background, EntityMap foreground, NumberWrapper timer, BooleanWrapper running) {
		this.simWindow = new SimulationWindow(DIM, newMapPane(background), newMapPane(foreground));
		this.info = new InfoWindow(CONTROL_WIDTH);
		this.mouseListener = new InfoListener(info);
		this.timer = timer;
		this.running = running;
		checkMaps(background.getContentsImmutable(), foreground.getContentsImmutable());
		createAndShowGUI();
	}

	private void createAndShowGUI() {
		/* Create and set up the window */
		JFrame frame = new JFrame("Rabbit Breeding");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(MAP_WIDTH + CONTROL_WIDTH, WINDOW_HEIGHT);
		frame.setResizable(false);

		/* Set up the map and the controls */
		frame.getContentPane().add(this.simWindow, BorderLayout.CENTER);
		JPanel controls = new ControlPane(this.info, this.timer, this.running, CONTROL_WIDTH);
		frame.getContentPane().add(controls, BorderLayout.LINE_END);

		/* Display the window */
		frame.setVisible(true);
	}

	private static void checkMaps(MapComponent[][] terrainArray, MapComponent[][] entitiesArray) {
		if (terrainArray.length != entitiesArray.length) {
			throw new ArrayIndexOutOfBoundsException("Both arrays must have the same number of rows");
		}
		for (int i = 0; i < terrainArray.length; i++) {
			if (entitiesArray[i].length != terrainArray[i].length) {
				throw new ArrayIndexOutOfBoundsException("Both arrays must have the same number of columns");
			}
		}
	}

	public void updateForeground(MapPane foreground) {
		this.simWindow.updateLayer(DIM, foreground, 1);
	}

	public void updateInfo() {
		this.info.updateInfo();
	}

	public MapPane newMapPane(MapContainer<? extends MapComponent> map) {
		return new MapPane(map, DIM, mouseListener);
	}

	public static Dimension getDimensions() {
		return DIM;
	}
}

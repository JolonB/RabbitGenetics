package main;

import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Entity;
import entities.EntityParam;
import map_container.EntityMap;
import map_container.MapComponent;
import map_container.TerrainMap;
import ui.Window;

public class Main {
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	private static final int ROWS = 50;
	private static final int COLS = 50;
	private static final int PERCENT_WATER = 40;
	private static final int NUM_RABBIT = 1;
	private static final int NUM_CABBAGE = 0;
	private static final int NUM_FOX = 0;
	private static final boolean UI_ACTIVE = true;
	/** Updated using the slider in the UI */
	public static double stepDuration = 3.0;

	public static void beginSimulationNoUI(TerrainMap terrain, EntityMap entities) {

	}

	public static void beginSimulation(TerrainMap terrain, EntityMap entities) {
		Window w = new Window(terrain, entities);
	}

	public static void calculateMovement(TerrainMap terrain, EntityMap entities) {
		Entity[][] ent = (Entity[][]) entities.getContents();
		for (Entity[] line : ent) {
			for (Entity e : line) {
				// TODO entity stuff
			}
		}
	}

	public static void main(String[] args) {
		EntityParam params = new EntityParam();
		params.numRabbit = NUM_RABBIT;
		params.numCabbage = NUM_CABBAGE;
		params.numFox = NUM_FOX;

		TerrainMap terrain = new TerrainMap(TerrainMap.generateSimplexMap(ROWS, COLS, PERCENT_WATER));
		EntityMap entities = new EntityMap(EntityMap.generateEntityMap(ROWS, COLS, terrain.getGrass(), params));

		if (UI_ACTIVE) {
			beginSimulation(terrain, entities);
		} else {
			beginSimulationNoUI(terrain, entities);
		}
		LOGGER.log(Level.INFO, "done");
	}

}

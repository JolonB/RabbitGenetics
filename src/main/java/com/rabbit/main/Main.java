package com.rabbit.main;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rabbit.entities.Action;
import com.rabbit.entities.Entity;
import com.rabbit.entities.EntityParam;
import com.rabbit.entities.Null;
import com.rabbit.map_container.EntityMap;
import com.rabbit.map_container.MapContainer;
import com.rabbit.map_container.TerrainMap;
import com.rabbit.terrain.Terrain;
import com.rabbit.ui.MapPane;
import com.rabbit.ui.Window;
import com.rabbit.wrapper.NumberWrapper;

public class Main {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	private static final int ROWS = 50;
	private static final int COLS = 50;
	private static final int PERCENT_WATER = 40;
	private static final int NUM_RABBIT = 1;
	private static final int NUM_CABBAGE = 0;
	private static final int NUM_FOX = 0;
	private static final boolean UI_ACTIVE = true;
	private static final long STEP_DURATION = 3000;
	private static boolean running;

	private static void beginSimulationNoUI(TerrainMap terrain, EntityMap entities) {
		NumberWrapper stepDuration = new NumberWrapper(Long.valueOf(STEP_DURATION));
		NumberWrapper timeoutUntil = new NumberWrapper();

		EntityMap newEntities = new EntityMap();
		getTimeout(timeoutUntil, stepDuration);
		while (running) {
			if (checkTimeout(timeoutUntil, stepDuration)) {
				/* Print map */
				System.out.println(MapContainer.toLayeredString(entities, terrain));
				/* Calculate next buffer */
				newEntities = doCalculate(terrain, entities);
				entities = newEntities;
			}
		}
	}

	private static void beginSimulation(TerrainMap terrain, EntityMap entities) {
		NumberWrapper stepDuration = new NumberWrapper(Long.valueOf(STEP_DURATION));
		NumberWrapper timeoutUntil = new NumberWrapper();
		Dimension dim = Window.getDimensions();
		MapPane background = new MapPane(terrain, dim);
		MapPane foreground = new MapPane(entities, dim);
		Window w = new Window(background, foreground, stepDuration);

		EntityMap newEntities = new EntityMap();
		getTimeout(timeoutUntil, stepDuration);
		while (running) {
			/* If the timeout period has elapsed */
			if (checkTimeout(timeoutUntil, stepDuration)) {
				/* Redraw buffer */
				w.updateForeground(foreground);
				/* Calculate next buffer */
				newEntities = doCalculate(terrain, entities);
				/* Update next buffer */
				foreground = new MapPane(newEntities, dim);
				entities = newEntities;
			}
		}
	}

	private static EntityMap doCalculate(TerrainMap terrain, EntityMap entities) {
		int rows = entities.getContents().length;
		int cols = entities.getContents()[0].length;

		/* Calculate actions */
		Action[][] actions = calculateMovement(terrain, entities);

		/* Parse actions into new entity map */
		EntityMap newEntities = new EntityMap(EntityMap.generateEmptyEntityMap(rows, cols));
		parseActions(actions, newEntities.getContents());
		return newEntities;
	}

	@Deprecated
	private static boolean doCalculate(TerrainMap terrain, EntityMap entities, NumberWrapper timeoutUntil,
			NumberWrapper stepDuration, EntityMap newEntities) {

		if (timeoutUntil.compareNum(System.currentTimeMillis()) < 0) { /* If timeStart is less than current time */
			int rows = entities.getContents().length;
			int cols = entities.getContents()[0].length;

			/* Calculate actions */
			Action[][] actions = calculateMovement(terrain, entities);

			/* Parse actions into new entity map */
			newEntities.setEntities(EntityMap.generateEmptyEntityMap(rows, cols));
			parseActions(actions, newEntities.getContents());

			/* Calculate new timeout */
			getTimeout(timeoutUntil, stepDuration);

			return true;
		}
		return false;
	}

	private static long getTimeout(long stepDuration) {
		return System.currentTimeMillis() + stepDuration;
	}

	private static void getTimeout(NumberWrapper timeoutUntil, long stepDuration) {
		timeoutUntil.setValue(System.currentTimeMillis() + stepDuration);
	}

	private static void getTimeout(NumberWrapper timeoutUntil, NumberWrapper stepDuration) {
		timeoutUntil.setValue(System.currentTimeMillis() + stepDuration.getValueLong());
	}

	/**
	 * Check whether the timeout period has elapsed. If it has, return true and
	 * update the timeout. Otherwise, return false.
	 * 
	 * @param timeoutUntil The system time (in milliseconds) that indicates the
	 *                     timeout is done.
	 * @param stepDuration The duration of the next timeout in milliseconds. For
	 *                     example, a stepDuration of 1000 means checkTimeout will
	 *                     return true if called after 1 second has elapsed.
	 * @return True if the timeout is done. Otherwise return false.
	 */
	private static boolean checkTimeout(NumberWrapper timeoutUntil, NumberWrapper stepDuration) {
		if (timeoutUntil.compareNum(System.currentTimeMillis()) < 0) { /* If timed out */
			getTimeout(timeoutUntil, stepDuration);
			return true;
		}
		return false;
	}

	private static Action[][] calculateMovement(TerrainMap terrain, EntityMap entities) {
		Terrain[][] terrainArray = terrain.getContentsImmutable();
		Entity[][] oldEntities = entities.getContentsImmutable();
		int rows = terrainArray.length;
		int cols = terrainArray[0].length;

		Entity[][] newEntities = new Entity[rows][cols];
		Action[][] actions = new Action[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				newEntities[i][j] = new Null(i, j);
				actions[i][j] = oldEntities[i][j].calculateAction(terrainArray);
			}
		}

		return actions;
	}

	private static void parseActions(Action[][] actions, Entity[][] newEntities) {
		int rows = actions.length;
		int cols = actions[0].length;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Action act = actions[i][j];
				switch (act.nextAction) {
					case EAT:
						break;
					case BREED:
						break;
					case MOVE:
						newEntities[act.x][act.y] = act.entity;
						act.entity.setPos(act.x, act.y);
						break;
					case DIE:
						break;
					case NOTHING:
						break;
					default:
						throw new UnsupportedOperationException("Action must be EAT, BREED, MOVE, DIE, or NOTHING.");
				}
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

		running = true;

		if (UI_ACTIVE) {
			beginSimulation(terrain, entities);
		} else {
			beginSimulationNoUI(terrain, entities);
		}
		LOGGER.log(Level.INFO, "done");
	}

}

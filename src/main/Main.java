package main;

import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Action;
import entities.Entity;
import entities.EntityParam;
import entities.Null;
import map_container.EntityMap;
import map_container.TerrainMap;
import terrain.Terrain;
import ui.Window;
import wrapper.NumberWrapper;

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
	/** Updated using the slider in the UI. In milliseconds */
	public static final long STEP_DURATION = 3000;
	public static boolean running = true;

	private static void beginSimulationNoUI(TerrainMap terrain, EntityMap entities) {
		long currentTime = getTimeout(STEP_DURATION);
		while (running) {
			if (currentTime < System.currentTimeMillis()) {
				calculateMovement(terrain, entities);
				currentTime = getTimeout(STEP_DURATION);
			}
		}
	}

	private static void beginSimulation(TerrainMap terrain, EntityMap entities) {
		NumberWrapper stepDuration = new NumberWrapper(Long.valueOf(STEP_DURATION));
		NumberWrapper timeStart = new NumberWrapper(0);
		Window w = new Window(terrain, entities, stepDuration);

		getTimeout(stepDuration, timeStart);
		while (running) {

		}
	}

	private static EntityMap doCalculate(TerrainMap terrain, EntityMap entities, NumberWrapper timeStart,
			NumberWrapper stepDuration) { //TODO rename current time to timeoutTime?
		if (timeStart.compareTo(System.currentTimeMillis()) < 0) { /* If timeStart is less than current time */
			calculateMovement(terrain, entities);
			getTimeout(stepDuration, timeStart);
		}
		return null;
	}

	private static long getTimeout(long stepDuration) {
		return System.currentTimeMillis() + stepDuration;
	}

	private static void getTimeout(NumberWrapper stepDuration, NumberWrapper timeStart) {
		timeStart.setValue(System.currentTimeMillis() + stepDuration.getValueLong());
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

				System.out.println(actions[i][j].toString());
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

		if (UI_ACTIVE) {
			beginSimulation(terrain, entities);
		} else {
			beginSimulationNoUI(terrain, entities);
		}
		LOGGER.log(Level.INFO, "done");
	}

}

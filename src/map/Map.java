package map;

import java.util.Random;

import open_simplex_noise.OpenSimplexNoise;

public class Map {

	static Terrain[][] terrain;
	static Random rand = new Random();

	/**
	 * Create a map object. This should not be changed once it is first generated.
	 * 
	 * @param terrain The layout of the ground.
	 * @throws Error
	 */
	public Map(Terrain[][] terrain) throws Error {
		int numRows = terrain.length;
		int numCols = terrain[0].length;
		if (numRows <= 0) {
			throw new Error("Number of rows must be equal to or greater than 1. Currently have " + numRows);
		}
		if (numCols <= 0) {
			throw new Error("Number of columns must be equal to or greater than 1. Currently have " + numCols);
		}

		for (Terrain[] line : terrain) {
			if (line.length != numCols) {
				throw new Error("Array must be square. Length 1 = " + numCols + ", length 2 = " + line.length);
			}
		}
		this.terrain = terrain;
	}

	public void draw() {
		for (int i = 0; i < terrain.length; i++) {
			for (int j = 0; j < terrain[0].length; j++) {
				// TODO draw
			}
		}
	}

	public String toString() {
		StringBuilder mapString = new StringBuilder();
		for (int i = 0; i < terrain.length; i++) {
			for (int j = 0; j < terrain[0].length; j++) {
				mapString.append(terrain[i][j].toChar());
			}
			mapString.append('\n');
		}
		return mapString.toString();
	}

	private static final double DIRECT_CONST = 0.003;
	private static final double DIAG_CONST = 0.003;

	/**
	 * TODO implement
	 * 
	 * @param rows
	 * @param cols
	 * @param percentWater
	 * @return
	 */
	public static Terrain[][] generateRandomMap(int rows, int cols, int percentWater) throws Error {
		if (rows <= 0) {
			throw new Error("Number of rows must be greater than 0");
		}
		if (cols <= 0) {
			throw new Error("Number of columns must be greater than 0");
		}
		if (percentWater < 0 || percentWater > 100) {
			throw new Error("Percentage of water must be between 0 and 100 inclusive");
		}

		terrain = new Terrain[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				terrain[i][j] = new Grass();
			}
		}
		double temp_percent;
		for (int pass = 0; pass < 5; pass++) {
			percentWater /= pass + 1;
			for (int i = 0; i < rows; i++) {
				boolean iMin = i > 0;
				boolean iMax = i < rows - 1;
				for (int j = 0; j < cols; j++) {
					boolean jMin = j > 0;
					boolean jMax = j < cols - 1;
					temp_percent = percentWater;

					/* Diagonals */
					if (iMin && jMin) {
						temp_percent += terrain[i - 1][j - 1].toChar() == 'W' ? (100 - temp_percent) * DIAG_CONST : 0;
					}
					if (iMin && jMax) {
						temp_percent += terrain[i - 1][j + 1].toChar() == 'W' ? (100 - temp_percent) * DIAG_CONST : 0;
					}
					if (iMax && jMin) {
						temp_percent += terrain[i + 1][j - 1].toChar() == 'W' ? (100 - temp_percent) * DIAG_CONST : 0;
					}
					if (iMax && jMax) {
						temp_percent += terrain[i + 1][j + 1].toChar() == 'W' ? (100 - temp_percent) * DIAG_CONST : 0;
					}
					/* Directs */
					if (iMin) {
						temp_percent += terrain[i - 1][j].toChar() == 'W' ? (100 - temp_percent) * DIRECT_CONST : 0;
					}
					if (jMin) {
						temp_percent += terrain[i][j - 1].toChar() == 'W' ? (100 - temp_percent) * DIRECT_CONST : 0;
					}
					if (iMax) {
						temp_percent += terrain[i + 1][j].toChar() == 'W' ? (100 - temp_percent) * DIRECT_CONST : 0;
					}
					if (jMax) {
						temp_percent += terrain[i][j + 1].toChar() == 'W' ? (100 - temp_percent) * DIRECT_CONST : 0;
					}
					if (temp_percent > rand.nextInt(100)) {
						terrain[i][j] = new Water();
					}
				}
			}
		}
		return terrain;
	}

	public static Terrain[][] generateSimplexMap(int rows, int cols, double waterHeight) {
		if (waterHeight > 1 || waterHeight < -1) {
			throw new Error("waterHeight must be between -1 and 1");
		}
		terrain = new Terrain[rows][cols];
		double rowD = (double) rows;
		double colD = (double) cols;
		// throw new UnsupportedOperationException("generateSimplexMap has not been
		// implemented yet");
		OpenSimplexNoise noiseGen = new OpenSimplexNoise(rand.nextLong());
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				terrain[i][j] = noiseGen.eval(4.0 * i / rows, 4.0 * j / cols, 0.1) <= waterHeight ? new Water()
						: new Grass();
			}
		}

		return terrain;
	}

}

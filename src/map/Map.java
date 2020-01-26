package map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import open_simplex_noise.OpenSimplexNoise;

public class Map {

	Terrain[][] terrain;
	List<Point> grass, sand, water;

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
		this.storeLand();
	}

	private void storeLand() {
		grass = new ArrayList<>();
		sand = new ArrayList<>();
		water = new ArrayList<>();

		Point p;
		for (int i = 0; i < this.terrain.length; i++) {
			for (int j = 0; j < this.terrain[0].length; j++) {
				p = new Point(i, j);
				switch (this.terrain[i][j].toChar()) {
				case 'G':
					grass.add(p);
					break;
				case 'S':
					sand.add(p);
					break;
				case 'W':
					water.add(p);
					break;
				default:
					throw new IllegalArgumentException("Did not expect " + this.terrain[i][j].toChar());
				}
			}
		}
	}

	/**
	 * Returns a copy of the terrain array. This array is technically mutable,
	 * however, any modifications to it will not affect the map.
	 * 
	 * @return A copy of the terrain
	 */
	public Terrain[][] getTerrainImmutable() {
		/* Do edge case of null first */
		if (this.terrain == null) {
			return null;
		}

		Terrain[][] newTerrain = new Terrain[this.terrain.length][];
		for (int i = 0; i < this.terrain.length; i++) {
			newTerrain[i] = Arrays.copyOf(this.terrain[i], this.terrain[i].length);
		}
		return newTerrain;
	}

	public Terrain[][] getTerrain() {
		return this.terrain;
	}

	public void draw() {
		for (int i = 0; i < this.terrain.length; i++) {
			for (int j = 0; j < this.terrain[0].length; j++) {
				// TODO draw
			}
		}
	}

	public String toString() {
		StringBuilder mapString = new StringBuilder();
		for (int i = 0; i < this.terrain.length; i++) {
			for (int j = 0; j < this.terrain[0].length; j++) {
				mapString.append(this.terrain[i][j].toChar());
			}
			mapString.append('\n');
		}
		return mapString.toString();
	}

//	private static final double DIRECT_CONST = 0.003;
//	private static final double DIAG_CONST = 0.003;

	/**
	 * No longer supported. Do not use.
	 * 
	 * @deprecated
	 */
	public static Terrain[][] generateRandomMap(int rows, int cols, int percentWater) throws Error {
		throw new UnsupportedOperationException(
				"generateRandomMap contains out of date code and is no longer supported.");
		/*
		 * if (rows <= 0) { throw new Error("Number of rows must be greater than 0"); }
		 * if (cols <= 0) { throw new Error("Number of columns must be greater than 0");
		 * } if (percentWater < 0 || percentWater > 100) { throw new
		 * Error("Percentage of water must be between 0 and 100 inclusive"); }
		 * 
		 * terrain = new Terrain[rows][cols]; for (int i = 0; i < rows; i++) { for (int
		 * j = 0; j < cols; j++) { terrain[i][j] = new Grass(10); } } double
		 * temp_percent; for (int pass = 0; pass < 5; pass++) { percentWater /= pass +
		 * 1; for (int i = 0; i < rows; i++) { boolean iMin = i > 0; boolean iMax = i <
		 * rows - 1; for (int j = 0; j < cols; j++) { boolean jMin = j > 0; boolean jMax
		 * = j < cols - 1; temp_percent = percentWater;
		 * 
		 * if (iMin && jMin) { temp_percent += terrain[i - 1][j - 1].toChar() == 'W' ?
		 * (100 - temp_percent) * DIAG_CONST : 0; } if (iMin && jMax) { temp_percent +=
		 * terrain[i - 1][j + 1].toChar() == 'W' ? (100 - temp_percent) * DIAG_CONST :
		 * 0; } if (iMax && jMin) { temp_percent += terrain[i + 1][j - 1].toChar() ==
		 * 'W' ? (100 - temp_percent) * DIAG_CONST : 0; } if (iMax && jMax) {
		 * temp_percent += terrain[i + 1][j + 1].toChar() == 'W' ? (100 - temp_percent)
		 * * DIAG_CONST : 0; } if (iMin) { temp_percent += terrain[i - 1][j].toChar() ==
		 * 'W' ? (100 - temp_percent) * DIRECT_CONST : 0; } if (jMin) { temp_percent +=
		 * terrain[i][j - 1].toChar() == 'W' ? (100 - temp_percent) * DIRECT_CONST : 0;
		 * } if (iMax) { temp_percent += terrain[i + 1][j].toChar() == 'W' ? (100 -
		 * temp_percent) * DIRECT_CONST : 0; } if (jMax) { temp_percent += terrain[i][j
		 * + 1].toChar() == 'W' ? (100 - temp_percent) * DIRECT_CONST : 0; } if
		 * (temp_percent > rand.nextInt(100)) { terrain[i][j] = new Water(10); } } } }
		 * return terrain;
		 */
	}

	public static Terrain[][] generateSimplexMap(int rows, int cols, int waterHeight) {
		if (waterHeight < 0 || waterHeight > 90) {
			throw new Error("waterHeight must be between 0 and 90");
		}
		Terrain[][] terrain = new Terrain[rows][cols];
		double heightMap[][] = new double[rows][cols];
		double values[] = new double[rows * cols];

		OpenSimplexNoise noiseGen = new OpenSimplexNoise(rand.nextLong());
		double height;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				height = noiseGen.eval(4.0 * i / rows, 4.0 * j / cols);
				heightMap[i][j] = height;
				values[i * cols + j] = height;
			}
		}

		Arrays.parallelSort(values);

		/* Get important values from array */
		double min = values[0]; // min value
		double percentile = values[(waterHeight * rows * cols) / 100]; // max height of sand
		double maxWater = percentile - (percentile - min) * 0.2; // max height of water
		// double max = values[rows * cols - 1]; // max value

		/* Create terrain array */
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				height = heightMap[i][j];
				if (height < maxWater) {
					terrain[i][j] = new Water();
				} else if (height < percentile) {
					terrain[i][j] = new Sand();
				} else {
					terrain[i][j] = new Grass();
				}
			}
		}

		return terrain;
	}

}

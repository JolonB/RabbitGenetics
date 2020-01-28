package map_container;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import open_simplex_noise.OpenSimplexNoise;
import terrain.Grass;
import terrain.Sand;
import terrain.Terrain;
import terrain.Water;

public class TerrainMap extends MapContainer {
	private static final Logger LOGGER = Logger.getLogger(TerrainMap.class.getName());

	private List<Point> grass;
	private List<Point> sand;
	private List<Point> water;

	static Random rand = new Random();

	/**
	 * Create a map object. This should not be changed once it is first generated.
	 * 
	 * @param terrain The layout of the ground.
	 * @throws Error
	 */
	public TerrainMap(Terrain[][] terrain) {
		super(terrain);
		this.storeLand();
	}

	private void storeLand() {
		grass = new ArrayList<>();
		sand = new ArrayList<>();
		water = new ArrayList<>();

		Point p;
		for (int i = 0; i < this.contents.length; i++) {
			for (int j = 0; j < this.contents[0].length; j++) {
				p = new Point(i, j);
				switch (this.contents[i][j].toChar()) {
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
					throw new IllegalArgumentException("Did not expect " + this.contents[i][j].toChar());
				}
			}
		}
	}

	public void draw() {
		for (int i = 0; i < this.contents.length; i++) {
			for (int j = 0; j < this.contents[0].length; j++) {
				// TODO draw
				throw new UnsupportedOperationException("draw not implemented");
			}
		}
	}

	public String toString() {
		StringBuilder mapString = new StringBuilder();
		for (int i = 0; i < this.contents.length; i++) {
			for (int j = 0; j < this.contents[0].length; j++) {
				mapString.append(this.contents[i][j].toChar());
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

	public List<Point> getGrass() {
		return Collections.unmodifiableList(grass);
	}

	public List<Point> getSand() {
		return Collections.unmodifiableList(sand);
	}

	public List<Point> getWater() {
		return Collections.unmodifiableList(water);
	}

}

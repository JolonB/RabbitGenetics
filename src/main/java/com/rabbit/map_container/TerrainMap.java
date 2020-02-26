package com.rabbit.map_container;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import com.open_simplex_noise.OpenSimplexNoise;
import com.rabbit.terrain.Grass;
import com.rabbit.terrain.Sand;
import com.rabbit.terrain.Terrain;
import com.rabbit.terrain.Water;

public class TerrainMap extends MapContainer<Terrain> {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(TerrainMap.class.getName());

	private transient List<Point> grass;
	private transient List<Point> sand;
	private transient List<Point> water;

	private static Random rand = new Random();

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

		Point point;
		for (int i = 0; i < this.contents.length; i++) {
			for (int j = 0; j < this.contents[0].length; j++) {
				point = new Point(i, j);
				switch (this.contents[i][j].toChar()) {
				case 'G':
					grass.add(point);
					break;
				case 'S':
					sand.add(point);
					break;
				case 'W':
					water.add(point);
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

	@Override
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

	public static Terrain[][] generateSimplexMap(int rows, int cols, int waterHeight) {
		if (waterHeight < 0 || waterHeight > 90) {
			throw new IllegalArgumentException("waterHeight must be between 0 and 90");
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

	@Override
	public Terrain[][] getContentsImmutable() {
		/* Copy array */
		Terrain[][] newContents = new Terrain[this.contents.length][];
		for (int i = 0; i < this.contents.length; i++) {
			newContents[i] = Arrays.copyOf(this.contents[i], this.contents.length);
		}

		return newContents;
	}

}

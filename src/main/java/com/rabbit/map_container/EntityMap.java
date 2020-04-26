package com.rabbit.map_container;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import com.rabbit.entities.Cabbage;
import com.rabbit.entities.Entity;
import com.rabbit.entities.EntityParam;
import com.rabbit.entities.Null;
import com.rabbit.entities.Rabbit;

public class EntityMap extends MapContainer<Entity> {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(EntityMap.class.getName());

	private static Random rand = new Random();

	public EntityMap() {
		super();
	}

	public EntityMap(Entity[][] entities) {
		super(entities);
	}

	public static Entity[][] generateEntityMap(int rows, int cols, List<Point> grass, EntityParam params) {
		List<Point> grassCopy = new ArrayList<>(grass);

		Entity[][] entities = generateEmptyEntityMap(rows, cols);

		int randIndex;
		Point point;
		for (int i = 0; i < params.numRabbit; i++) {
			randIndex = rand.nextInt(grassCopy.size());
			point = grassCopy.remove(randIndex);
			entities[point.y][point.x] = new Rabbit(point.x, point.y);
		}
		for (int i = 0; i < params.numCabbage; i++) {
			randIndex = rand.nextInt(grassCopy.size());
			point = grassCopy.remove(randIndex);
			entities[point.y][point.x] = new Cabbage(point.x, point.y);
		}
		// for (int i = 0; i < params.numFox; i++) {
		// randIndex = rand.nextInt(grassCopy.size());
		// p = grassCopy.get(randIndex);
		// grassCopy.remove(randIndex);
		// entities[p.y][p.x] = new Fox(p.x, p.y);
		// }

		return entities;
	}

	public static Entity[][] generateEntityMap(int rows, int cols, EntityParam params) {
		List<Point> points = new ArrayList<>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				points.add(new Point(i, j));
			}
		}
		return generateEntityMap(rows, cols, points, params);
	}

	public static Entity[][] generateEmptyEntityMap(int rows, int cols) {
		Entity[][] entities = new Entity[rows][cols];
		Null nullEnt = new Null();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				entities[i][j] = nullEnt;
			}
		}
		return entities;
	}

	@Override
	public Entity[][] getContentsImmutable() {
		/* Copy array */
		Entity[][] newContents = new Entity[this.contents.length][];
		for (int i = 0; i < this.contents.length; i++) {
			newContents[i] = Arrays.copyOf(this.contents[i], this.contents.length);
		}

		return newContents;
	}

}

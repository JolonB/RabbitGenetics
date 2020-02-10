package map_container;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import entities.Entity;
import entities.EntityParam;
import entities.Null;
import entities.Rabbit;

public class EntityMap extends MapContainer<Entity> {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(EntityMap.class.getName());

	static Random rand = new Random();

	public EntityMap() {
		super();
	}

	public EntityMap(Entity[][] entities) {
		super(entities);
	}

	public void setEntities(Entity[][] entities) {
		validateContents(entities);

		this.contents = entities;
	}

	public static Entity[][] generateEntityMap(int rows, int cols, List<Point> grass, EntityParam params) {
		List<Point> grassCopy = new ArrayList<>(grass);

		Entity[][] entities = generateEmptyEntityMap(rows, cols);

		int randIndex;
		Point p;
		for (int i = 0; i < params.numRabbit; i++) {
			randIndex = rand.nextInt(grassCopy.size());
			p = grassCopy.get(randIndex);
			grassCopy.remove(randIndex);
			entities[p.x][p.y] = new Rabbit(p.x, p.y);
		}
//		for (int i = 0; i < params.numCabbage; i++) {
//			randIndex = rand.nextInt(grassCopy.size());
//			p = grassCopy.get(randIndex);
//			grassCopy.remove(randIndex);
//			entities[p.x][p.y] = new Cabbage(p.x, p.y); 
//		}
//		for (int i = 0; i < params.numFox; i++) {
//			randIndex = rand.nextInt(grassCopy.size());
//			p = grassCopy.get(randIndex);
//			grassCopy.remove(randIndex);
//			entities[p.x][p.y] = new Fox(p.x, p.y); 
//		}

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
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				entities[i][j] = new Null(i, j);
			}
		}
		return entities;
	}

	public Entity[][] getContentsImmutable() {
		/* Copy array */
		Entity[][] newContents = new Entity[this.contents.length][];
		for (int i = 0; i < this.contents.length; i++) {
			newContents[i] = Arrays.copyOf(this.contents[i], this.contents.length);
		}

		return newContents;
	}

}

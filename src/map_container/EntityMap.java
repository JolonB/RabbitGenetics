package map_container;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entities.Entity;
import entities.EntityParam;
import entities.Null;
import entities.Rabbit;

public class EntityMap extends MapContainer {

	static Random rand = new Random();

	public EntityMap(Entity[][] entities) throws Error {
		super(entities);
	}

	public static Entity[][] generateEntityMap(int rows, int cols, List<Point> grass, EntityParam params) {
		List<Point> grassCopy = new ArrayList<>(grass);

		Entity[][] entities = new Entity[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				entities[i][j] = new Null();
			}
		}

		int randIndex;
		Point p;
		for (int i = 0; i < params.numRabbit; i++) {
			randIndex = rand.nextInt(grassCopy.size());
			p = grassCopy.get(randIndex);
			grassCopy.remove(randIndex);
			entities[p.x][p.y] = new Rabbit();
		}
//		for (int i = 0; i < params.numCabbage; i++) {
//			randIndex = rand.nextInt(grassCopy.size());
//			p = grassCopy.get(randIndex);
//			grassCopy.remove(randIndex);
//			entities[p.x][p.y] = new Cabbage(); 
//		}
//		for (int i = 0; i < params.numFox; i++) {
//			randIndex = rand.nextInt(grassCopy.size());
//			p = grassCopy.get(randIndex);
//			grassCopy.remove(randIndex);
//			entities[p.x][p.y] = new Fox(); 
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

}

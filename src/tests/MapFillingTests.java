package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import terrain.*;
import terrain_container.Map;

class MapFillingTests {

	@Test
	void allGround() {
		int dim = 10;
		Terrain[][] terrain = new Terrain[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				terrain[i][j] = new Grass();
			}
		}
		Map m = new Map(terrain);

		assertTrue(m.toString().matches("[G\n]+"));
	}
	
	@Test
	void testMapMaking() {
		Map m = new Map(Map.generateRandomMap(20, 50, 20));
		System.out.println(m.toString());
	}

}

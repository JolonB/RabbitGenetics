package com.map_container;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.rabbit.map_container.TerrainMap;
import com.rabbit.terrain.Grass;
import com.rabbit.terrain.Terrain;

class MapFillingTests {

	@Test
	public void testAllGround() {
		int dim = 10;
		Terrain[][] terrain = new Terrain[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				terrain[i][j] = new Grass();
			}
		}
		TerrainMap map = new TerrainMap(terrain);

		assertTrue(map.toString().matches("[G\n]+"), "Map was modified while creating the TerrainMap");
	}

	@Test
	public void testGeneratingAllGround() {
		int dim = 100;
		TerrainMap map = new TerrainMap(TerrainMap.generateSimplexMap(dim, dim, 0));

		assertTrue(map.toString().matches("[G\n]+"), "Map does not consist of only grass");
	}

	@Test
	public void testGeneratingHalfGround() {
		int dim = 500;
		TerrainMap map = new TerrainMap(TerrainMap.generateSimplexMap(dim, dim, 50));
		String terrainString = map.toString();

		int groundCount = 0;
		char grassChar = new Grass().toChar();
		for (int i = 0; i < terrainString.length(); i++) {
			if (terrainString.charAt(i) == grassChar) {
				groundCount++;
			}
		}

		int expected = (int) Math.ceil(dim * dim / 2.0);
		double deviation = expected * 0.001;
		assertTrue(Math.abs(groundCount - expected) <= deviation,
				"Ground count (" + groundCount + ") must be between " + expected + "+/-" + deviation);
	}

	@Test
	public void testNullMap() {
		assertThrows(IllegalArgumentException.class, () -> new TerrainMap(null), "Need to provide a contents array");
	}
}

package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import map_container.TerrainMap;
import terrain.*;

class MapFillingTests {

	@Test
	void testAllGround() {
		int dim = 10;
		Terrain[][] terrain = new Terrain[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				terrain[i][j] = new Grass();
			}
		}
		TerrainMap m = new TerrainMap(terrain);

		assertTrue(m.toString().matches("[G\n]+"));
	}

	@Test
	void testGeneratingAllGround() {
		int dim = 100;
		TerrainMap m = new TerrainMap(TerrainMap.generateSimplexMap(dim, dim, 0));

		assertTrue(m.toString().matches("[G\n]+"));
	}

	@Test 
	void testGeneratingHalfGround() {
		int dim = 500;
		TerrainMap m = new TerrainMap(TerrainMap.generateSimplexMap(dim, dim, 50));
		String terrainString = m.toString();

		int groundCount = 0;
		for (int i = 0; i < terrainString.length(); i++) {
			if (terrainString.charAt(i) == 'G') {
				groundCount++;
			}
		}

		int expected = (int) Math.ceil(dim * dim / 2.0);
		double deviation = expected * 0.001;
		assertTrue("Ground count (" + groundCount + ") must be between " + expected + "+/-" + deviation,
				Math.abs(groundCount - expected) <= deviation);
	}

	@Test
	void testNullMap() {
		Throwable t = assertThrows(NullPointerException.class, () -> new TerrainMap(null));

		assertEquals("Need to provide a contents array", t.getMessage());
	}

}

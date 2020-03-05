package com.map_container;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rabbit.entities.Cabbage;
import com.rabbit.entities.Entity;
import com.rabbit.entities.Null;
import com.rabbit.entities.Rabbit;
import com.rabbit.map_container.MapComponent;
import com.rabbit.terrain.Grass;
import com.rabbit.terrain.Water;

class MapComponentTests {

    @Test
    public void testEqualsGrass() {
        Grass grass1 = new Grass(0, 0);
        Grass grass2 = new Grass(0, 0);
        assertEquals(grass1, grass2, "Any two grass classes should be equal");
    }

    @Test
    public void testEqualsWater() {
        Water water1 = new Water(0, 0);
        Water water2 = new Water(0, 0);
        assertEquals(water1, water2, "Any two water classes should be equal");
    }

    @Test
    public void testNotEqualsRabbit() {
        Rabbit rabbit1 = new Rabbit(0, 0);
        Rabbit rabbit2 = new Rabbit(0, 0);
        assertFalse(rabbit1.equals(rabbit2), "Two different rabbit classes should not be equal");
    }

    @Test
    public void checkSurroundingCells() {
        Entity[][] entities = setUpTestBoard();

        List<Point> expected = new ArrayList<>(Arrays.asList(new Point(2, 1), new Point(2, 2), new Point(3, 3)));
        List<Point> actual = MapComponent.checkSurroundingCells(entities[3][2], entities, Cabbage.class);

        assertEquals(expected, actual);
    }

    private Entity[][] setUpTestBoard() {
        Entity[][] entities = new Entity[10][10];
        for (int i = 0; i < entities.length; i++) {
            for (int j = 0; j < entities[0].length; j++) {
                entities[i][j] = new Null();
            }
        }

        /* Add some things to the board */
        entities[3][2] = new Rabbit(3, 2);
        entities[4][1] = new Rabbit(4, 1);
        entities[0][2] = new Cabbage(3, 2);
        entities[2][1] = new Cabbage(2, 1);
        entities[2][2] = new Cabbage(2, 2);
        entities[3][3] = new Cabbage(3, 3);
        entities[4][0] = new Cabbage(4, 0);

        return entities;
    }

}
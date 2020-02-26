package com.map_container;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.rabbit.entities.Rabbit;
import com.rabbit.terrain.Grass;
import com.rabbit.terrain.Water;

class MapComponentTests { 

    @Test
    public void testEqualsGrass() {
        Grass grass1 = new Grass();
        Grass grass2 = new Grass();
        assertEquals(grass1, grass2, "Any two grass classes should be equal");
    }

    @Test
    public void testEqualsWater() {
        Water water1 = new Water();
        Water water2 = new Water();
        assertEquals(water1, water2, "Any two water classes should be equal");
    }

    @Test
    public void testNotEqualsRabbit() {
        Rabbit rabbit1 = new Rabbit(0, 0);
        Rabbit rabbit2 = new Rabbit(0, 0);
        assertFalse(rabbit1.equals(rabbit2), "Two different rabbit classes should not be equal");
    }

}
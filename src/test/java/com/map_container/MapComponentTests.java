package com.map_container;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.rabbit.entities.Rabbit;
import com.rabbit.terrain.Grass;
import com.rabbit.terrain.Water;

class MapComponentTests { 

    @Test
    void testEqualsGrass() {
        Grass g1 = new Grass();
        Grass g2 = new Grass();
        assertTrue(g1.equals(g2));
    }

    @Test
    void testEqualsWater() {
        Water w1 = new Water();
        Water w2 = new Water();
        assertTrue(w1.equals(w2));
    }

    @Test
    void testNotEqualsRabbit() {
        Rabbit r1 = new Rabbit(0, 0);
        Rabbit r2 = new Rabbit(0, 0);
        assertTrue(!r1.equals(r2));
    }

}
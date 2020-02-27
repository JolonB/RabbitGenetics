package com.rabbit.rabbit_breeding;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Logger;

public class BreedingSimulation {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(BreedingSimulation.class.getName());
	private static final Random RAND = new Random();

	/**
	 * 
	 * @param parent1
	 * @param parent2
	 * @param bias    A number between -1 and 1 inclusive which represents the bias
	 *                toward a parent. -1 means the child will be the most similar
	 *                to parent1; +1 means the child is closer to parent2; 0 is fair
	 *                breeding.
	 * @return
	 */
	public static Map<String, Float> breed(Map<String, Float> parent1, Map<String, Float> parent2, float bias) {
		Map<String, Float> child = new TreeMap<>();

		if (!parent1.keySet().equals(parent2.keySet())) {
			throw new IllegalArgumentException("Both parents need to have the same genes");
		}

		for (String gene : parent1.keySet()) {
			float childGene = getChildGene(parent1.get(gene), parent2.get(gene), bias);
			childGene = mutateGene(childGene);
			child.put(gene, childGene);
		}

		return child;
	}

	private static float getChildGene(float parent1Gene, float parent2Gene, float bias) {
		float dominantGene = getDominantGene(bias);
		return parent1Gene * (1 - dominantGene) + parent2Gene * dominantGene;
	}

	private static float mutateGene(float childGene) {
		return childGene; // TODO write mutation code
	}

	private static float getDominantGene(float bias) {
		float prob = RAND.nextFloat(); // get normally distributed value from 0 to 1
		// Just trust me on these. Feel free to plot it yourself with prob ranging from
		// 0 to 1. I recommend Desmos
		float biasScale = 0.25f - prob / 2.0f;
		float biasIntercept = 0.25f + prob / 2.0f;
		// sx^2 + (s+i)x + i
		return biasScale * bias * bias + (biasScale + biasIntercept) * bias + biasIntercept;
	}
}

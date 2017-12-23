package assignment4;

import java.util.Random;
import java.util.Vector;

/**
 * Class representing a population of individuals
 */
public class Population extends Vector<Individual> {
	private Map map;
		
	/**
	 * Actual standard ctor.
	 * @param map The map.
	 * @param initialSize The initial size of the population.
	 */
	Population(Map map, int initialSize) {
		for(int i = 0; i < initialSize; ++i)
		{
			add(new Individual(map));
		}
	}
	
	/**
	 * Standard ctor.
	 * @param map The map.
	 */
	public Population(Map map) {
		this(map, 0);
	}
	
	/**
	 * Randomly selects an individual out of the population
	 * proportionally to its fitness.
	 * @return The selected individual.
	 */
	Individual randomSelection() {
		double totalFitness = 0;
		int[] random = new int[100];
		int counter = 0;
		Random rand = new Random();
		// TODO implement random selection
		// an individual should be selected with a probability
		// proportional to its fitness
		for (int i = 0; i < this.size(); i++) {
			totalFitness += this.elementAt(i).getFitness();
		}
		
		for (int j = 0; j < this.size(); j++) {
			this.elementAt(j).probability = (int) ((this.elementAt(j).getFitness() / totalFitness) * 100);
			
			while (counter < this.elementAt(j).probability) {
				random[counter] = j;
				counter++;
			}
		}
		
		int number = rand.nextInt(100);
		Individual chosen = this.elementAt(random[number]);
		
		return chosen;
	}
	
}

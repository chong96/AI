package assignment4;

import java.util.Random;

public class Individual {
			private Map map; // the map
			private double fitness; // fitness is cached and only updated on request whenever necessary
			// TODO some representation of the genom of the individual
			int[] colors = new int[10];
			int possibleColors = 3;
			Random rand = new Random();
			int conflicts = 0;
			int probability = 0;

			/**
			 * Updates the fitness value based on the genom and the map.
			 */
			public void updateFitness() {
				// TODO implement fitness function
				fitness = 0;
				conflicts = 0;
				
				for (int i = 0; i < map.borders.size(); i++) {
						if (this.colors[map.borders.get(i).index1] == (this.colors[map.borders.get(i).index2])) {
							conflicts++;
						}
				}
				fitness = map.borders.size() - conflicts;
			}

			/**
			 * Default ctor. Creates a (valid) random individual.
			 * @param map The US states map.
			 */
			public Individual(Map map) {
				this.map = map;
			
				// TODO implement random generation of an individual
				for (int i = 0; i < 10; i++) {
					this.colors[i] = rand.nextInt(4) + 1;
				}

				updateFitness();				
			}

			/**
			 * Reproduces a child randomly from two individuals (see textbook).
			 * @param x The first parent.
			 * @param y The second parent.
			 * @return The child created from the two individuals.
			 */
			public static Individual reproduce(Individual x, Individual y) {
				Individual child = new Individual(x.map);
				int i = 0;
				Random rand = new Random();
				int split = rand.nextInt(x.colors.length);
				
				while (i < split) {
					child.colors[i] = x.colors[i];
					i++;
				}
				while (i < 10) {
					child.colors[i] = y.colors[i];
					i++;
				}

				// TODO reproduce child from individuals x and y

				child.updateFitness();
				return child;
			}

			/**
			 * Returns the current fitness value of the individual.
			 * @return The current fitness value.
			 */
			public double getFitness() {
				return fitness;
			}

			/**
			 * Randomly mutates the individual.
			 */
			public void mutate() {
				// TODO implement random mutation of the individual
				for (int i = 0; i < map.states.size(); i++) {
					int chance = rand.nextInt(10);
					if (chance < 1) {
						this.colors[i] = rand.nextInt(4) + 1;
					}
				}
				updateFitness();
			}

			/**
			 * Checks whether the individual represents a valid goal state.
			 * @return Whether the individual represents a valid goal state.
			 */
			public boolean isGoal() {
				return fitness == map.borders.size();
			}
			
			/**
			 * Prints out the individual to the console.
			 */
			void print() {
				// TODO implement printing the individual in the following format:
				// fitness: 15
				// North Carolina: 0
				// South Carolina: 2
				// ...
				System.out.printf("Fitness: %d\n", (int) this.fitness);
				for (int i = 0; i < colors.length; i++) {
					System.out.printf("%s: %d\n", this.map.states.elementAt(i), colors[i]);
				}
	}
}

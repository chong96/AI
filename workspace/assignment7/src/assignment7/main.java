package assignment7;

import java.util.Arrays;
import java.util.Random;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int count = 0;
		Random rand = new Random();
		double total = 0;
		double median = 0;
		double mean = 0;
		int[] array = new int[10000];
		while (count < 10000) {
				int var1, var2, var3 = 0;
				var1 = rand.nextInt(4);
				var2 = rand.nextInt(4);
				var3 = rand.nextInt(4);
				if(var1 == var2 && var2 == var3) {
					if(var1 == 0) {
						total += 20;
						array[count] = 20;
					} else if (var1 == 1) {
						total += 15;
						array[count] = 15;
					} else if (var1 == 2) {
						total += 5;
						array[count] = 5;
					} else if (var1 == 3) {
						total += 3;
						array[count] = 3;
					}
				} else if (var1 == 3 || var2 == 3 || var3 == 3) {
					int cherryCount = 0;
					if(var1 == 3) {
						cherryCount++;
					}
					if(var2 == 3) {
						cherryCount++;
					}
					if(var3 == 3) {
						cherryCount++;
					}
					total += cherryCount;
					array[count] = cherryCount;
				} else {
					array[count] = 0;
				}
				count++;
		}
		mean = total / 10000;
		Arrays.sort(array);
		median = array[array.length/2];
		System.out.printf("Median: %f\nMean: %f", median,mean);
	}

}

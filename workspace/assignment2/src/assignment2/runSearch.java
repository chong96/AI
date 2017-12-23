package assignment2;

import java.util.ArrayList;

public class runSearch {

	public static void main(String[] args) {
		
		Search s = new Search();
		ArrayList solutions = s.getSolutionStates(new State("Root", 3, 3, false, 1), new State("Goal", 0, 0, true, 100));

		if (solutions.size() == 0) {
			System.out.println("No solution was found.");
		} else {
			int Solfound = 1;
	        for (int i = 0; i < solutions.size(); i++)
	        {
	          State solutionState = (State)solutions.get(i);
	          System.out.println("=====SOLUTION FOUND=====\r\n");
	          System.out.println("This solution was found at level [" + 
	                            solutionState.getStateLevel() + "]\r\n");
	          solutionState.print();
	          System.out.println("\r\n");
	        }
		}
	}

}

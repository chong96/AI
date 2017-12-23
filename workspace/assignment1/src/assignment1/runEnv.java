package assignment1;

public class runEnv {

	public static void main(String[] args) {
		
		Environment environment = new Environment();
		Agent agent = new Agent();
		int count = 0;
		int score = 0;
		agent.setLocation("Left");
		String location = agent.getLocation();
		
		System.out.println("Welcome to the automatic vaccuum service! Starting now...");
		System.out.println("The program will terminate when it iterates through 4 clean spots.\n");
		
		System.out.println("Agent Location: " + location);
		
		while (count != 3) {
			
			location = agent.getLocation();
			if(environment.isDirty(location)) {
				System.out.println("Spot Status: Dirty");
				System.out.println("Processing...\n");
				agent.execute(location,environment);
				score += 5;
				score -= 1;
				count = 0;
			
			} else {
				System.out.println("Spot Status: Clean");
				System.out.println("Processing...\n");
				agent.execute(location,environment);
				score -= 1;
				count++;
			}
		
			System.out.printf("Agent Location: %s\nLeft Spot: %s\nRight Spot: %s\nScore: %d", 
					agent.getLocation(), environment.getLeft(), environment.getRight(), score);
			System.out.println("\n");
		
		}
		
		System.out.println("Reached 4 clean spots in a row, terminating.");
		
	}

}

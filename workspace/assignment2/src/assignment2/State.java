package assignment2;

public class State {

	public int numberOfCan, numberOfMiss;
	public boolean side;
	private int numberOfEachAtStart = 3;
	private String name;
	private State prevState;
	private int stateLevel = 0;
	
	public State (String name, int nMiss, int nCan, boolean side, int stateLevel) {
		
		this.name = name;
		this.numberOfMiss = nMiss;
		this.numberOfCan = nCan;
		this.side = side;
		this.prevState = null;
		this.stateLevel = stateLevel;
		
	}
	
	public State (String name, int nMiss, int nCan, boolean side, State prevState, int stateLevel) {
		
		this.name = name;
		this.numberOfMiss = nMiss;
		this.numberOfCan = nCan;
		this.side = side;
		this.prevState = prevState;
		this.stateLevel = stateLevel;
	}
	
	public int getStateLevel() {
		return this.stateLevel;
	}
	
	public String getStateName() {
		return this.name;
	}
	
	public void print() {
		if (prevState != null) {
			prevState.print();
		}
		String whatSide = side ? "  Boat" : "Boat  ";
		
		System.out.println(numberOfMiss + "M/" + numberOfCan + "C " + whatSide + " " + 
		(numberOfEachAtStart - numberOfMiss) + "M/" + (numberOfEachAtStart - numberOfCan) + "C");
		
	}
	
	public boolean equals(State stateToCheck) {
		
		return (numberOfMiss == stateToCheck.numberOfMiss && numberOfCan == stateToCheck.numberOfCan &&
				side == stateToCheck.side);
		
	}
	
	public boolean invalidState() {
		
		if (numberOfMiss < 0 || numberOfCan < 0 || numberOfMiss > numberOfEachAtStart || numberOfCan > numberOfEachAtStart) {
			return true;
		}
		if (numberOfMiss < numberOfCan && numberOfMiss > 0) {
			return true;
		}
		if ((numberOfEachAtStart - numberOfMiss) < (numberOfEachAtStart - numberOfCan) && 
				(numberOfEachAtStart - numberOfMiss) > 0) {
			return true;
		}
		return false;
	}
	
}

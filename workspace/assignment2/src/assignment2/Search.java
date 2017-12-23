package assignment2;

import java.util.ArrayList;

public class Search {

	private int currentRootState = 0;
	private ArrayList searchAgenda = new ArrayList();
	

	public Search () {
	
	}

	public void addStateToAgenda(String stateName, State parent, int nMiss, int nCan) {
		
		int boatDirection = parent.side ? 1 : -1;
		String newStateName = parent.getStateName() + stateName;
		
		State newState = new State (newStateName, parent.numberOfMiss + nMiss * boatDirection, 
				parent.numberOfCan + nCan * boatDirection, !parent.side, parent, parent.getStateLevel() + 1);
		
		addStateToAgenda(newState);
	}
	
	public void addStateToAgenda(State newState) {
		
		if (newState.invalidState()) {
			return;
		}
		searchAgenda.add(newState);
	}
	
	public ArrayList getSolutionStates (State startState, State endState) {
		
		int optimalSolutionLevel = 0;
		boolean allOptimalSolutionsFound = false;
		boolean foundFirstSolution = false;
		
		ArrayList solutions = new ArrayList();
		
		addStateToAgenda(startState);
		
		while (searchAgenda.size() > 0 && !allOptimalSolutionsFound) {
			
			State currentState = (State) searchAgenda.get(currentRootState);
			
			searchAgenda.remove(currentRootState);
			
			if(currentState.equals(endState)) {
				
				if(foundFirstSolution) {
					
					if(currentState.getStateLevel() < optimalSolutionLevel) {
						solutions.add(currentState);
					} else {
						allOptimalSolutionsFound = true;
					}
					
				} else {
					foundFirstSolution = true;
					optimalSolutionLevel = currentState.getStateLevel();
					solutions.add(currentState);
				}
				
			} else {
				generateSucessors(currentState);
			}
			
		}
		return solutions;
	}
	
	public void generateSucessors(State currentState) {
		
		int nCan = 0;
		int nMiss = 0;
		int stateName = 1;
		
		for (int i = 0; i <= 2; i++) {
			
			for(int j = 0; j <= 2; j++) {
				
				if (i == 0 && j == 0) {
					continue;
				}
				if (i + j > 2) {
					break;
				}
				nMiss = i;
				nCan = j;
				addStateToAgenda(" " + stateName++, currentState, nMiss, nCan);
			}
		}
	}
}
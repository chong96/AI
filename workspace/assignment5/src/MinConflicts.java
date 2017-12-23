import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implements the min-conflict local search CSP solver 
 */
public class MinConflicts implements CSPSolver {

	@Override
	public <E> CSPResult<E> solve(CSP<E> csp) {
		/* TODO
		 * find reasonable value for max iterations.
		 */
		return minConflicts(csp, 1000000);
	}
	
	/**
	 * Implements the min-conflicts algorithm.
	 * @param csp The csp to solve
	 * @param maxSteps The max number of steps
	 * @return A solution to the csp, or null if none was found.
	 */
	private static <E> CSPResult<E> minConflicts(CSP<E> csp, int maxSteps) {
		/* TODO
		 * The implementation can pretty much follow the pseudo code
		 * in the text book:
		 * 
		 * current <- an initial complete assignment for csp
		 * for i = 1 to max_steps do
		 *   if current is a solution for csp then return current
		 *   var <- a randomly chosen conflicted variable from csp.VARIABLES
		 *   value <- the value v for var that minimizes CONFLICTS(var, v, current, csp)
		 *   set var = value in current
		 * return failure
		 * 
		 * Most of it is straight forward, because there is a separate
		 * function for it. Only finding the value that minimizes the
		 * number of conflicts is a little more complicated. However,
		 * you should use the function conflicts() for this purpose.
		 * Also, please note that "failure" is "null" in this implementation.
		 * You should return the result like "new CSPResult(current, i);"
		 */
		
		Assignment<E> current = createCompleteAssignment(csp);

		for (int i = 1; i <= maxSteps; i++) {
			boolean isConsistent = true;
			for (int j = 0; j < csp.constraints.size(); j++) {
				Constraint constraint = csp.constraints.get(j);
				if(!constraint.isConsistent(current)) {
					isConsistent = false;
					break;
				}
			}
			if(isConsistent) {
				return new CSPResult(current,i);
			}
			String var = getRandomConflictedVariable(current, csp);
			int minimum = 1000000;
			E valueChange = null;
			for (int k = 0; k < csp.domains.get(var).size(); k++) {
				int conflicts = conflicts(var, csp.domains.get(var).get(k),current,csp);
				if (conflicts < minimum) {
					minimum = conflicts;
					valueChange = csp.domains.get(var).get(k);
				}
			}
			current.replace(var, valueChange);
		}
		return null;
	}
	
	/**
	 * Randomly selects a conflicted variable.
	 * @param current The current assignment
	 * @param csp The csp
	 * @return A randomly chosen conflicted variable.
	 */
	private static <E> String getRandomConflictedVariable(Assignment<E> current, CSP<E> csp) {
		/* TODO
		 * First, you should create an initially empty set of conflicted variables.
		 * Then, iterate over all constraints, and if it is not consistent, add
		 * all the variables from its scope to the set of conflicted variables.
		 * Afterwards, just return a randomly selected one of them. (Hint: make
		 * sure that variables that appear in multiple constraints are not
		 * selected with a higher probability, they should be selected unbiased).
		 */
		ArrayList<String> randomConflicted = new ArrayList<String>();
		
		for (int i = 0; i < csp.constraints.size(); i++) {
			Constraint constraint = csp.constraints.get(i);
			if(!constraint.isConsistent(current)) {
				List<String> scope = constraint.getScope();
				for (int j = 0; j < scope.size(); j++) {
					if (!randomConflicted.contains(scope.get(j))) {
						randomConflicted.add(scope.get(j));
					}
				}
			}
		}
		Random rand = new Random();
		int randomVariable = rand.nextInt(randomConflicted.size());
		return randomConflicted.get(randomVariable);
	}
	
	/**
	 * Creates an assignment in which every varibale is assigned a value from its domain.
	 * @param csp The underlying csp that defines the domains and the variables
	 * @return A complete assignment
	 */
	private static <E> Assignment<E> createCompleteAssignment(CSP<E> csp) {
		/* TODO
		 * create a new assignment and randomly assign a value to every
		 * variable from its domain.
		 */
		Assignment<E> completeAssignment = new Assignment<E>();
		for (int i = 0; i < csp.variables.size(); i++) {
			Random rand = new Random();
			int randomValue = rand.nextInt(csp.domains.get(csp.variables.get(i)).size());
			completeAssignment.put(csp.variables.get(i), csp.domains.get(csp.variables.get(i)).get(randomValue));
		}
		return completeAssignment;
	}
	
	/**
	 * Computes the number of conflict based on an assignment, but with one variable
	 * set to a specific value.
	 * @param var The variable to be checked
	 * @param value The value to be checked
	 * @param current The current assignment used as basis
	 * @param csp The csp problem
	 * @return The number of conflict given the current assignment, but with var=value
	 */
	private static <E> int conflicts(String var, E value, Assignment<E> current, CSP<E> csp) {
		/* TODO
		 * You might want to temporarily modify the assignment
		 * to set var = value (undo this afterwards!). Then
		 * iterate over all constraints and count the number of
		 * insonsistent constraints.
		 */
		E previousValue = current.get(var);
		current.replace(var, value);
		int constraintCount = 0;
		for (int i = 0; i < csp.constraints.size(); i++) {
			Constraint constraint = csp.constraints.get(i);
			if (!constraint.isConsistent(current)) {
				constraintCount++;
			}
		}
		current.replace(var, previousValue);
		return constraintCount;
	}
}
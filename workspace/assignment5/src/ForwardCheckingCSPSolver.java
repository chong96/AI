import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Implements the backtracking search with forward checking. 
 */
public class ForwardCheckingCSPSolver extends BacktrackingCSPSolver {
	
	/**
	 * Implements the actual forward checking. Infers the values to be deleted
	 * from the domains of some variables based on the given variable and value.
	 */
	@Override
	protected <E> Inference<E> inference(CSP<E> csp, Assignment<E> assignment, String var, E value) {
		/* TODO
		 * Implement the forward checking. You may want to iterate over all
		 * constraints to identify those who involve the given variable. Then,
		 * iterate over the variables of the scope of the constraint and check
		 * if the variable is not yet assigned. If it is not assigned, check all
		 * the values of the domain of that variable, and identify those values
		 * that are inconsistent with the constraint (therefore, you might temporarily
		 * modify the assignment with the value to test, and restore the assignment
		 * later on). The inconsistent values should be added to the inference that
		 * will be returned. If no value was found at all, then return failure (null in this
		 * case).
		 */
		Inference<E> inference = new Inference<E>();
		Set<E> set = new HashSet<E>();
		ArrayList<String> touchingStates = new ArrayList<String>();
		for (int i = 0; i < csp.constraints.size(); i++) {
			Constraint constraint = csp.constraints.get(i);
			java.util.List<String> stateList = constraint.getScope();			
			if (stateList.contains(var)) {
				java.util.List<String> states = constraint.getScope();
				for (int j = 0; j < states.size(); j++) {
					if (states.get(j) != var) {
						touchingStates.add(states.get(j));
					}
				}
			}
		}
		//System.out.println(touchingStates.toString());
		
		for (int i = 0; i < touchingStates.size(); i++) {
			String currentState = touchingStates.get(i);
			if (assignment.get(currentState) == null) {
				ArrayList<E> list = (ArrayList<E>) orderDomainValues(csp,currentState,assignment);
				//System.out.println(list.toString());
				set = new HashSet<E>();
				for (int j = 0; j < list.size(); j++) {
					assignment.put(currentState, list.get(j));
					if (!csp.isConsistent(assignment)) {
						set.add(list.get(j));
						//System.out.println("reached");
					}
					assignment.remove(currentState, list.get(j));
				}
			}
			if (set.isEmpty()) {
				continue;
			}
			inference.put(currentState, set);
		}
		return inference;
	}
}

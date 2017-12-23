import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

public class AC3 {
	/**
	 * This class represents a single arc for the AC-3 algorithm.
	 */
	public static class Arc {
		private String value1, value2;

		public Arc(String value1, String value2) {
			this.value1 = value1;
			this.value2 = value2;
		}
	}
	
	/**
	 * Implements the AC-3 algorithm to make a csp arc consistent.
	 * @param csp The csp
	 * @return Whether an inconsistency was found (false) or not (true)
	 * @throws Exception
	 */
	public static <E> boolean ac3(CSP<E> csp) throws Exception {
		/* TODO
		 * First, set up a queue of all arcs. For each constraint (you can assume that
		 * all constraints are binary constraints) add two arcs, one forward, and one
		 * backwards. Then implement the following (taken from text book):
		 * 
		 * while queue is not empty do
		 *   (Xi, Xj) <- REMOVE-FIRST(queue)
		 *   if REVISE(csp, Xi, Xj) then
		 *     if size of Di = 0 then return false
		 *     for each Xk in Xi.NEIGHBORS - {Xj} do
		 *       add (Xk, Xi) to queue
		 * return true
		 * 
		 * Note that Xi and Xj correspond to Arc.value1 and Arc.value2
		 * after some arc has been polled from the queue.
		 */
		LinkedList<Arc> arcQueue = new LinkedList<Arc>();
		for (int i = 0; i < csp.constraints.size(); i++) {
			Constraint constraint = csp.constraints.get(i);
			java.util.List<String> list = constraint.getScope();
			Arc forward = new Arc(list.get(0), list.get(1));
			Arc backward = new Arc(list.get(1), list.get(0));
			arcQueue.add(forward);
			arcQueue.add(backward);
		}
		
		while (!arcQueue.isEmpty()) {
			Arc arc = arcQueue.poll();
			if (revise(csp,arc.value1,arc.value2)) {
				if (csp.domains.get(arc.value1).isEmpty()) {
					return false;
				}
				ArrayList<?> neighbors = (ArrayList) neighbors(csp, arc.value1);
				for (int i = 0; i < neighbors.size(); i++) {
					arcQueue.add(new Arc(neighbors.get(i).toString(),arc.value1));
				}
			}
		}
		return false;
	}
	
	/**
	 * Implements the revise-routine of the AC-3 algorithm. Effectively iterates
	 * over all domain values of var1 and checks if there is at least 1 possible value
	 * for var2 remaining. If not, removes that value from the domain of var1.
	 * @param csp
	 * @param var1
	 * @param var2
	 * @return
	 */
	private static <E> boolean revise(CSP<E> csp, String var1, String var2) {
		/* TODO
		 * You may want to use a temporary Assignment to check whether a constraint
		 * is violated by any values for var1 and var2. Iterate over all domain values
		 * of var1. Then iterate over all domain values of var2 and prepare the
		 * temporary assignment accordingly. If all values for var2 produce an
		 * inconsistent assignment, remove the current value from the domain of
		 * var1. Hint: You cannot modify the domain as long as you are iterating over
		 * it, therefore I recommend to temporarily store the values to be deleted in
		 * a list or something, and then delete them all together after you iterated
		 * over all domain values. Also, don't forget to return whether you actually
		 * modified the domain of var1. 
		 */
		Assignment<E> temp = new Assignment<E>();
		java.util.List<E> list1 = csp.domains.get(var1);
		java.util.List<E> list2 = csp.domains.get(var2);
		ArrayList deleteList = new ArrayList();
 		boolean revised = false;
		
		for (int i = 0; i < list1.size(); i++) {
			temp.put(var1, list1.get(i));
			for (int j = 0; j < list2.size(); j++) {
				temp.put(var2, list2.get(j));
				if(csp.isConsistent(temp)) {				
					deleteList.add(list1.get(i));
					break;
				}
			}
		}
		
		if (deleteList.size() < list1.size()) {
			for (int k = 0; k < deleteList.size(); k++) {
				csp.domains.get(var1).remove(deleteList.get(k));
			}
			revised = true;
		}
		
		return revised;
	}
	
	/**
	 * Computes the "neighbors" of a variable in a CSP. A variable is
	 * a neighbor if it is coupled to another variable by a constraint.
	 * @param csp The csp
	 * @param var The variable the neighbors of which are to be found.
	 * @return The neighbors of the given variable.
	 */
	private static Set<String> neighbors(CSP<?> csp, String var) {
		/* TODO
		 * Iterate over all constraints and check if var is contained
		 * in the constraint's scope. If so, all _other_ variables of
		 * the constraint's scope are neighbors.
		 */
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < csp.constraints.size(); i++) {
			Constraint constraint = csp.constraints.get(i);
			java.util.List<String> list = constraint.getScope();
			if(list.contains(var)) {
				for (int j = 0; j < list.size(); j++) {
					if (list.get(j) != var) {
						set.add(list.get(j));
					}
				}
			}
		}
		return set;
	}
}

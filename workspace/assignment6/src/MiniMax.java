import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * A class that implements the MiniMax algorithm.
 */
public class MiniMax {
	private static int numberOfStates; /**< counter to measure the number of iterations / states. */
	private static boolean usePruning;
	
	/**
	 * Start procedure of the MiniMax algorithm.
	 * @param state The state where the MiniMax algorithm starts searching
	 * @param usePruning Whether to use alpha-beta-pruning
	 * @return An optimal action to be taken at this point.
	 */
	public static Action MinimaxDecision(State state, boolean usePruning) {
		MiniMax.usePruning = usePruning;
		numberOfStates = 0;
		
		/* TODO
		 * Implement the minimax decision routine. Iterate over all possible actions
		 * and evaluate their utilities invoking MinValue(). Return the action that
		 * generates the highest utility.
		 * You can just return the first or the last best action, however it makes
		 * the algorithm way more interesting if you determine all best actions
		 * and then select one of them randomly.
		 */

		List<Action> actions = state.getActions();
		float bestUtility = 0;
		int index = 0;
		
		for (int i = 0; i < actions.size(); i++) {
			TicTacToeAction action = (TicTacToeAction) actions.get(i);
			float v = MinValue(state.getResult(action), 10000, -10000);
			if (v >= bestUtility) {
				bestUtility = v;
				index = i;
			}
		}

		System.out.println("State space size: " + numberOfStates);
		return actions.get(index);
	}
	
	/**
	 * @param state The current state to be evaluated
	 * @param alpha The current value for alpha
	 * @param beta The current value for beta
	 * @return The maximum of the utilites invoking MinValue, or the utility of the state if it is a leaf.
	 */
	private static float MaxValue(State state, float alpha, float beta) {
		++numberOfStates;
		
		/*
		 * TODO implement the MaxValue procedure according to the textbook:
		 * 
		 * function Max-Value(state, alpha, beta) return a utility value
		 *   if TERMINAL-TEST(state) then return UTILITY(state)
		 *   v <- -infinity
		 *   for each a in ACTIONS(State) do
		 *     v <- max(v, MIN-VALUE(RESULT(state, a), alpha, beta))
		 *     if MiniMax.usePruning then
		 *       if v >= beta then return v
		 *       alpha <- max(alpha, v)
		 *   return v
		 *   
		 *   The pseudo code is slightly changed to be able to reuse the
		 *   code for alpha-beta-pruning.
		 */
		if (state.isTerminal()) {
			return state.getUtility();
		}
		
		float v = -100000;
		
		List<Action> list = state.getActions();
		for (int i = 0; i < list.size(); i++) {
			TicTacToeAction action = (TicTacToeAction) list.get(i);
			State tempState = state.getResult(action);
			v = Math.max(v, MinValue(tempState, alpha, beta));
			if (MiniMax.usePruning) {
				if (v >= beta) {
					return v;
				}
				alpha = Math.max(alpha, v);
			}
		}
		return v;
	}
	
	/**
	 * @param state The current state to be evaluated
	 * @param alpha The current value for alpha
	 * @param beta The current value for beta
	 * @return The minimum of the utilites invoking MaxValue, or the utility of the state if it is a leaf.
	 */
	private static float MinValue(State state, float alpha, float beta) {
		++numberOfStates;

		/*
		 * TODO implement the MaxValue procedure according to the textbook:
		 * 
		 * function Min-Value(state, alpha, beta) return a utility value
		 *   if TERMINAL-TEST(state) then return UTILITY(state)
		 *   v <- +infinity
		 *   for each a in ACTIONS(State) do
		 *     v <- min(v, MAX-VALUE(RESULT(state, a), alpha, beta))
		 *     if MiniMax.usePruning then
		 *       if v <= alpha then return v
		 *       beta <- min(beta, v)
		 *   return v
		 *   
		 *   The pseudo code is slightly changed to be able to reuse the
		 *   code for alpha-beta-pruning.
		 */

		if (state.isTerminal()) {
			return state.getUtility();
		}
		
		float v = 100000;
		
		List<Action> list = state.getActions();
		for (int i = 0; i < list.size(); i++) {
			TicTacToeAction action = (TicTacToeAction) list.get(i);
			State tempState = state.getResult(action);
			v = Math.min(v, MaxValue(tempState, alpha, beta));
			if (MiniMax.usePruning) {
				if (v <= alpha) {
					return v;
				}
				beta = Math.min(beta, v);
			}
		}
		return v;
	}
}

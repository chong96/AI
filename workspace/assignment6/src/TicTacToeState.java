import java.util.ArrayList;
import java.util.List;

/**
 * A class that implements a state and the playing logic of the TicTacToe game. 
 */
public class TicTacToeState implements State {
	private Square[] field; /**< The field, consisting of nine squares. First three values correspond to first row, and so on. */
	public Square player; /**< The player, either X or O. */
	public Square playerToMove; /**< The player that is about to move. */
	private float utility; /**< The utility value of this state. Can be 0, 1 (won) or -1 (lost).*/

	/**
	 * Updates the utility value.
	 */
	private void updateUtility() {
		/** TODO
		 * The utility value for the TicTacToe game is defined as follows:
		 * - if player has three marks in a row, it is 1
		 * - if the other player has three marks in a row, it is -1
		 * - otherwise it is 0
		 * Note that "three marks in a row" can actually be a row, a column
		 * or a diagonal. So basically, first find out if there are three
		 * identical values in a row, and if so, check whether the marks belong
		 * to player or not. 
		 */
		boolean row1Won = field[0] == field[1] && field[0] == field [2] && !field[0].equals(Square.EMPTY);
		boolean row2Won = field[3] == field[4] && field[3] == field [5] && !field[3].equals(Square.EMPTY);
		boolean row3Won = field[6] == field[7] && field[6] == field [8] && !field[6].equals(Square.EMPTY);
		boolean col1Won = field[0] == field[3] && field[0] == field [6] && !field[0].equals(Square.EMPTY);
		boolean col2Won = field[1] == field[4] && field[1] == field [7] && !field[1].equals(Square.EMPTY);
		boolean col3Won = field[2] == field[5] && field[2] == field [8] && !field[2].equals(Square.EMPTY);
		boolean diag1Won = field[0] == field[4] && field[0] == field [8] && !field[0].equals(Square.EMPTY);
		boolean diag2Won = field[2] == field[4] && field[2] == field [6] && !field[2].equals(Square.EMPTY);
		
		if (row1Won || row2Won || row3Won || col1Won || col2Won || col3Won || diag1Won || diag2Won) {
			
			if (row1Won) {
				if (field[0] == player) {
					utility = 1;
				} else {
					utility = -1;
				}
			}
			
			if (row2Won) {
				if (field[3] == player) {
					utility = 1;
				} else {
					utility = -1;
				}
			}
			
			if (row3Won) {
				if (field[6] == player) {
					utility = 1;
				} else {
					utility = -1;
				}
			}
			
			if (col1Won) {
				if (field[0] == player) {
					utility = 1;
				} else {
					utility = -1;
				}
			}
			
			if (col2Won) {
				if (field[1] == player) {
					utility = 1;
				} else {
					utility = -1;
				}
			}
			
			if (col3Won) {
				if (field[2] == player) {
					utility = 1;
				} else {
					utility = -1;
				}
			}
			
			if (diag1Won) {
				if (field[0] == player) {
					utility = 1;
				} else {
					utility = -1;
				}
			}
			
			if (diag2Won) {
				if (field[2] == player) {
					utility = 1;
				} else {
					utility = -1;
				}
			}
			
		} else {
			utility = 0;
		}
		
	}
	
	/**
	 * Default constructor.
	 */
	public TicTacToeState() {
		field = new Square[9];
		for(int i = 0; i < 9; ++i) {
			field[i] = Square.EMPTY;
		}
		player = Square.X;
		playerToMove = Square.X;
		utility = 0;
	}
	
	@Override
	public List<Action> getActions() {
		/** TODO
		 * For the TicTacToe game, there is one valid action
		 * for each empty square. The action would then consist
		 * of the position of the empty square and the "color" of
		 * the player to move.
		 */
		List<Action> list = new ArrayList<Action>();
		for (int i = 0; i < field.length; i++) {
			if (field[i].equals(Square.EMPTY)) {
				list.add(new TicTacToeAction(playerToMove,i));
			}
		}
		return list;
	}

	@Override
	public float getUtility() {
		return utility;
	}

	public State getResult(Action action) {
		/** TODO
		 * Create a new state and copy all the contents of the current state
		 * to the new one (in particular the field and the player). The
		 * player to move must be switched. Then incorporate the action into
		 * the field of the new state. Finally, compute the utility of the new
		 * state using updateUtility().
		 */
		TicTacToeAction ticAction = (TicTacToeAction) action;
		TicTacToeState newState = new TicTacToeState();
		
		newState.player = player;
		
		if(playerToMove == Square.O) {
			newState.playerToMove = Square.X;
		} else {
			newState.playerToMove = Square.O;
		}
		
		for (int i = 0; i < field.length; i++) {
			newState.field[i] = field[i];
		}
		
		newState.field[ticAction.position] = ticAction.player;
		
		newState.updateUtility();
		
		return newState;
	}

	@Override
	public boolean isTerminal() {
		/** TODO
		 * Hint: the utility value has specific values if one of
		 * the players has won, which is a terminal state. However,
		 * you will also have to check for terminal states in which
		 * no player has won, which can not be inferred immediately
		 * from the utility value.
		 */
		if (utility == 1 || utility == -1) {
			return true;
		}
		
		for (int i = 0; i < field.length; i++) {
			if (field[i].equals(Square.EMPTY)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void print() {
		String s = "" + field[0] + "|" + field[1] + "|" + field[2] + "\n";
		s += "-+-+-\n";
		s += field[3] + "|" + field[4] + "|" + field[5] + "\n";
		s += "-+-+-\n";
		s += field[6] + "|" + field[7] + "|" + field[8] + "\n";
		System.out.println(s);
	}
}

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SudokuGame.java
 *  Purpose       :  Implementing the interactive game environment
 *  @author       :  Ona Igbinedion
 *  Date written  :  2020-06-13
 *  Notes         :  None right now.  We'll add some as they occur.
 *  Warnings      :  None right now.  We'll add some as they occur.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  -------------  -----------------------------------------------------------
 *  @version 1.0.0  2020-06-13  Onariaginosa   Initial writing
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package sudoku;

import java.util.*;

public class SudokuGame {
	// fields

	private int[][] state;
	private final int OPEN = 0;

	// Constructor

	/**
	 * Tests if the initial start state is a valid SudokuGame. It does not test the
	 * correctness of the initial state;
	 * 
	 * @param StartState the initial game state. It is of type int[][]
	 */
	SudokuGame(int[][] StartState) {
		if (isValidGame(StartState)) {
			this.state = StartState;
		} else {
			this.state = null;
		}
	}

	/**
	 * Tests whether the given state is valid. It must be a 9 by 9 2D-int array
	 * where none of the values are greater than 9 or less than 0.
	 * 
	 * @param state The given state to test.
	 * @return A boolean representation of whether the given state is valid
	 */
	public boolean isValidGame(int[][] state) {
		if (state.length != 9) {
			return false;
		}
		for (int i = 0; i < 9; i++) {
			if (state[i].length != 9) {
				return false;
			}
		}
		if (!testRows(state)) {
			return false;
		}
		if (!testColumns(state)) {
			return false;
		}
		if (!testGroups(state)) {
			return false;
		}
		return true;
	}

	/**
	 * Edits the state at position (x,y) to the given value if values given are
	 * valid
	 * 
	 * @param x     The index of the row value
	 * @param y     The index of the column value
	 * @param value The value to be assigned at the given position
	 */
	public void editState(int x, int y, int value) {
		// edits the state if the value is valid
		if (value >= 0 && value < 10) {
			state[y][x] = value;
		}
	}

	/**
	 * Method that determines whether no spots are open in a game
	 * 
	 * @return a boolean representation of whether the game is complete or not
	 */
	public boolean isComplete() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (this.state[i][j] == this.OPEN) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Method that determines if the current state is the same as a given state
	 * 
	 * @param other The state that is to be compared
	 * @return A boolean representation of whether the current state is the same as
	 *         the other state
	 */
	public boolean isStateEqual(int[][] other) {
		if ((other == null && this.state != null) || (other != null && this.state == null)) {
			return false;
		} else if (other == null && this.state == null) {
			return true;
		} else if (state.length != other.length) {
			return false;
		} else {
			for (int i = 0; i < 9; i++) {

				if (state[i].length != other[i].length) {
					return false;
				}
				for (int j = 0; j < 9; j++) {
					if (state[i][j] != other[i][j]) {
						System.out.println("Location: (" + i + "," + j + ")");
						System.out.println("State: " + state[i][j]);
						System.out.println("Other: " + other[i][j]);
						return false;
					}
				}
			}
			return true;
		}
	}

	/**
	 * Helper Method that tests for duplicate numbers in each row. 0's don't count
	 * 
	 * @param state the given state to be tested
	 * @return A boolean representation of whether the rows are valid in the given
	 *         state.
	 */
	private boolean testRows(int[][] state) {
		int[] rowValueFrequencies = new int[9];
		int index;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				index = state[i][j] - 1;
				if (index >= 0 && index < 9) {
					rowValueFrequencies[index]++;
				} else if (index != -1) {
					// values are invalid
					return false;
				}
			}
			for (int freq : rowValueFrequencies) {
				if (freq > 1) {
					return false;
				}
			}
			rowValueFrequencies = new int[9];
		}
		return true;
	}

	/**
	 * Helper Method that tests for duplicate numbers in each column. 0's don't
	 * count
	 * 
	 * @param state the given state to be tested
	 * @return A boolean representation of whether the columns are valid in the
	 *         given state.
	 */
	private boolean testColumns(int[][] state) {
		int[] colValueFrequencies = new int[9];
		int index;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				index = state[j][i] - 1;
				if (index >= 0 && index < 9) {
					colValueFrequencies[index]++;
				} else if (index != -1) {
					// values are invalid
					return false;
				}
			}
			for (int freq : colValueFrequencies) {
				if (freq > 1) {
					return false;
				}
			}
			colValueFrequencies = new int[9];
		}
		return true;
	}

	/**
	 * Helper Method that tests for duplicate numbers in each group. 0's don't count
	 * 
	 * @param state the given state to be tested
	 * @return A boolean representation of whether the groups are valid in the given
	 *         state.
	 */
	private boolean testGroups(int[][] state) {
		int[] groupValueFrequencies = new int[9];
		int index;
		for (int x = 0; x < 9; x += 3) {
			for (int y = 0; y < 9; y += 3) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						index = state[x + i][y + j] - 1;
						if (index >= 0 && index < 9) {
							groupValueFrequencies[index]++;
						} else if (index != -1) {
							// values are invalid
							return false;
						}
					}
				}
				for (int freq : groupValueFrequencies) {
					if (freq > 1) {
						return false;
					}
				}
				groupValueFrequencies = new int[9];
			}
		}
		return true;
	}

	/**
	 * Method that determines whether the current state is a win
	 * 
	 * @return A boolean representation of whether the current state is a win
	 */
	public boolean isWin() {
		if (!isComplete()) {
			return false;
		}
		if (!isValidGame(this.state)) {
			return false;
		}
		return true;
	}

	/**
	 * Method that returns the current state
	 * 
	 * @return A 2-D int array representation of the current state
	 */
	public int[][] getState() {
		return this.state;
	}

}
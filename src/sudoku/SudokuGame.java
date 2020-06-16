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

	public int[][] state;
	private final int OPEN = 0;

	// constructor

	SudokuGame(int[][] StartState) {
		if (isValidGame(StartState)) {
			this.state = StartState;
		} else {
			this.state = null;
		}
	}

	// Methods
	/**
	 * 
	 */

	public boolean isValidGame(int[][] state) {
		if (state.length != 9) {
			return false;
		}
		for (int i = 0; i < 9; i++) {
			if (state[i].length != 9) {
				return false;
			}
			for (int j = 0; j < 9; j++) {
				if (state[i][j] > 9 || state[i][j] < 0) {
					return false;
				}
			}
		}
		return true;
	}

	public void editState(int x, int y, int value) {
		// edits the state if the value is valid
		if (value > 0 && value < 10) {
			state[x][y] = value;
		}
	}

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

	private boolean testRows() {
//		HashMap <Integer, HashSet<Integer>> rows = new HashMap<Integer, HashSet<Integer>>(); 
		HashSet<Integer> rowValues = new HashSet<Integer>();
		for (int i = 0; i < 9; i++) {
			// collect the row values
			for (int j = 0; j < 9; j++) {
				rowValues.add(this.state[i][j]);
			}
			if (rowValues.size() != 9) {
				return false;
			} else {
				rowValues.clear();
			}
		}
		return true;
	}

	private boolean testColumns() {
//		HashMap <Integer, HashSet<Integer>> rows = new HashMap<Integer, HashSet<Integer>>(); 
		HashSet<Integer> colValues = new HashSet<Integer>();
		for (int i = 0; i < 9; i++) {
			// collect the row values
			for (int j = 0; j < 9; j++) {
				colValues.add(this.state[j][i]);
			}
			if (colValues.size() != 9) {
				return false;
			} else {
				colValues.clear();
			}
		}
		return true;
	}

	private boolean testGroups() {
		HashSet<Integer> groupValues = new HashSet<Integer>();
		int x = 0;
		int y = 0;
		while (x < 9) { 
			while (y < 9) {
				System.out.println("NewGroup: \n \n \n");
				for (int i = 0; i <= 3; i++) {
					for (int j = 0; j <= 3; j++) {
						groupValues.add(this.state[x + i][y + j]);
						System.out.print("(" + (x+i) + "," + (y+j) + "), ");
						
					}
				}
				if (groupValues.size() != 9) {
					return false;
				} else {
					groupValues.clear();
				}
				y+= 3;
			}
			x += 3;
		}
		return true;
	}

	public boolean isWin() {
		if (!isComplete()) {
			return false;
		} 
		if (!testRows()) {
			return false;
		}
		if (!testColumns()) {
			return false;
		}
		if (!testGroups()) {
			return false;
		}
		return true;
	}

}
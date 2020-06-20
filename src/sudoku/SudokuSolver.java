/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  sudoku_solver.java
 *  Purpose       :  Implements the AI sudoku solver
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

import java.util.HashMap;
import java.util.HashSet;

public class SudokuSolver {
	//Fields
	
//	private static final int[] VALIDNUMBERS = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	private static final int OPEN = 0;
	
	
	//Methods
	
	/**
	 * Method that solves the entire sudoku game. If there is more than 1 possible solution 
	 * the sudoku game would be considered incorrect and would return null. If the sudoku game is 
	 * solved incorrectly, the method would return null
	 * 
	 * @param game 				the 9x9 sudoku game of type SudokuGame
	 * @return					returns the solved sudokuGame state object of type int[][] or null
	 */
	public static int[][] solver(SudokuGame game) {
		boolean go = true;
		
		while (go) {
			go = updateState(game, getPossibleStates(game));
		}
		if (game.isComplete()) {
			if (game.isWin()) {
				return game.getState();
			} else {
				System.out.println("The solver did not solve the game correctly. I apologize. There is debugging to be done");
				return null;
			}
		}
		
		return null;
	}
	
	
	
	
	private static boolean updateState(SudokuGame game, HashMap<Coordinate, HashSet<Integer>> possibilities) {
		boolean updated = false;
		// basic checks
		for (Coordinate c : possibilities.keySet()) {
			if (possibilities.get(c).size() == 1) {
				updated = true;
				game.editState(c.x, c.y, (int) possibilities.get(c).toArray()[0]);
			}
		}
		return updated;
	}
	
	private static HashMap<Coordinate, HashSet<Integer>> getPossibleStates(SudokuGame game) {
		int[][] state = game.getState();
		HashMap<Coordinate, HashSet<Integer> > possibilityMap = new HashMap<Coordinate, HashSet<Integer>>();
		Coordinate location;
		HashSet<Integer> values;
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if(state[y][x] == OPEN) {
					location = new Coordinate(x, y);
					values = getPossibleValues(game, x, y);
					possibilityMap.put(location, values);			
				}
			}
		}
		return possibilityMap;
	}
	
	private static HashSet<Integer> getPossibleValues( SudokuGame game, int x, int y) {
		int[][] state = game.getState();
		int groupX = x/3;
		int groupY = y/3;
		HashSet<Integer> possibilities = new HashSet<Integer>();
		for (int i = 1; i <10; i++) {
			possibilities.add(i);
		}
		for (int i = 0; i < 9; i++) {
			// check row 
			if (possibilities.contains(state[y][i])) {
				possibilities.remove(state[y][i]);
			}
			// check column
			if (possibilities.contains(state[i][y])) {
				possibilities.remove(state[i][x]);
			}
		}
		//check groups
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (possibilities.contains(state[groupY+j][groupX+i])) {
					possibilities.remove(state[groupY+j][groupX+i]);
				}
			}
		}
		return possibilities;
	}
	
	public static class Coordinate {
		public int x;
		public int y;
		
		Coordinate(int X, int Y) {
			this.x = X;
			this.y = Y;
		}
	}
	
	
}
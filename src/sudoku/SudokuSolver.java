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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SudokuSolver {
	// Fields

//	private static final int[] VALIDNUMBERS = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	private static final int OPEN = 0;

	

	
	
	
	
	// Methods

	/**
	 * Method that solves the entire sudoku game. If there is more than 1 possible
	 * solution the sudoku game would be considered incorrect and would return null.
	 * If the sudoku game is solved incorrectly, the method would return null
	 * 
	 * @param game the 9x9 sudoku game of type SudokuGame
	 * @return returns the solved sudokuGame state object of type int[][] or null
	 */
	public static int[][] solver(SudokuGame game) {
		boolean go = true;
		
		while (go) {
			go = (updateState(game, getPossibleStates(game))|| OnlyOneOption(game, getPossibleStates(game)) );
		}
		if (game.isComplete()) {
			if (game.isWin()) {
				return game.getState();
			} else {
				System.out.println("The solver did not solve the game correctly. I apologize. There is debugging to be done");
				return null;
			}
		}
		System.out.println("The solver cannot solve this game ");
		return null;
	}
	
	
	/**
	 * Implementing a key system for the possibility map where the key is a number based on the Integer groups.
	 * If the Integer is (3,4) where x = 3 and y = 4, then the resulting key is "34"
	 * 
	 * @param x			The x value of the Integer to be located
	 * @param y			The y value of the Integer to be located
	 * @return			The numerical key representing the Integer to be located.
	 */
	private static int getKey(int x, int y) {
		String X = Integer.toString(x);
		String Y = Integer.toString(y);
		String key = X+Y;
		return Integer.parseInt(key);
	}
	
	/**
	 * Method to obtain the x Integer from a given key integer.
	 * @param key		The Integer of the location in int form
	 * @return			The x Integer from the key
	 */
	private static int getX(int key) {
//		use integer division to get the x value.
		return key/10;
	}
	
	private static int getY(int key) {
//		use modulo to get the y value
		return key%10;
	}
	

	/**
	 * Method that updates the state until all Integer positions are either
	 * filled, or the remaining Integers have more than one possible solution.
	 * 
	 * @param game          The SudokuGame to be solved
	 * @param possibilities The possibility map for each position in the game.
	 * @return returns true if the state was updated and false if not.
	 */
	private static boolean updateState(SudokuGame game, HashMap<Integer, HashSet<Integer>> possibilities) {
		boolean updated = false;
//		trash is to remove the keus
		HashSet<Integer> trash = new HashSet<Integer>();
		// basic checks
		for (Integer key : possibilities.keySet()) {
			if (possibilities.get(key).size() == 1) {
				updated = true;
				game.editState(getX(key), getY(key), (int) possibilities.get(key).toArray()[0]);
				trash.add(key);
			}
		}
		for (Integer c : trash) {
			possibilities.remove(c);
		}
		return updated;
	}

	/**
	 * Method that gets the possibilities of each position in the state
	 * 
	 * @param game The SudokuGame in which the possibilities must be generated
	 * @return The possibility map of the given game's state.
	 */
	
	private static HashMap<Integer, HashSet<Integer>> getPossibleStates(SudokuGame game) {
		int[][] state = game.getState();
		HashMap<Integer, HashSet<Integer>> possibilityMap = new HashMap<Integer, HashSet<Integer>>();
		Integer location;
		HashSet<Integer> values;
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (state[y][x] == OPEN) {
					location = getKey(x,y);
					values = getPossibleValues(game, location);
					possibilityMap.put(location, values);
				}
			}
		}
		return possibilityMap;
	}

	/**
	 * Method the gets the possible values at a given Integer
	 * 
	 * @param game The SudokuGame to be considered
	 * @param pos  The position in the SudokuGame state
	 * @return A HashSet of the possible chosen positions
	 */
	private static HashSet<Integer> getPossibleValues(SudokuGame game, int pos) {
		int[][] state = game.getState();
		int groupX = (getX(pos) / 3 == 0) ? 0 : (getX(pos)  / 3 == 1) ? 3 : 6;
		int groupY = (getY(pos)  / 3 == 0) ? 0 : (getY(pos)  / 3 == 1) ? 3 : 6;
		HashSet<Integer> possibilities = new HashSet<Integer>();
		for (int i = 1; i < 10; i++) {
			possibilities.add(i);
		}
		for (int i = 0; i < 9; i++) {
			// check row
			if (possibilities.contains(state[getY(pos) ][i])) {
				possibilities.remove(state[getY(pos) ][i]);
			}
			// check column
			if (possibilities.contains(state[i][getY(pos) ])) {
				possibilities.remove(state[i][getX(pos) ]);
			}
		}
		// check groups
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (possibilities.contains(state[groupY + j][groupX + i])) {
					possibilities.remove(state[groupY + j][groupX + i]);
				}

			}
		}
		return possibilities;
	}
	
	private static boolean OnlyOneOption(SudokuGame game, HashMap<Integer, HashSet<Integer>> possibilities) {
		return (OnlyOneOptionRow(game, possibilities) || OnlyOneOptionCol(game, possibilities) || OnlyOneOptionGroup(game, possibilities));
	}
	

	private static boolean OnlyOneOptionGroup(SudokuGame game, HashMap<Integer, HashSet<Integer>> possibilities) {
		// if the possibility appears once in a group then that value is the possibility
		int[][] state = game.getState();
		
		HashMap<Integer, Integer> finalEdits = new HashMap<Integer, Integer>();
		ArrayList<Integer> group = new ArrayList<Integer>();
		int[] locations = new int[9];
		int[] freq = new int[9];
		for (int xOffset = 0; xOffset < 9; xOffset += 3) {
			for (int yOffset = 0; yOffset < 9; yOffset += 3) {
				// collect group
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (state[yOffset + j][xOffset + i] == 0) {
							for (Integer key : possibilities.keySet()) {
								if (getX(key) == xOffset + i && getY(key) == yOffset + j) {
									group.add(key);
									break;
								}
							}
						}
					}
				}
				// check for singular possibility in group
				for (int key : group) {
					for (int v : possibilities.get(key)) {
						freq[v - 1]++;
						locations[v - 1] = key;
					}
				}
				for (int f = 0; f < 9; f++) {
					if (freq[f] == 1) {
						finalEdits.put(locations[f], f + 1);
						// if we found the value, no need to iterate through it
						// when searching for Integers in the following groups
						possibilities.remove(locations[f]);
					}
				}

				// reset group
				group = new ArrayList<Integer>();
			}
		}
		// edit all of the chosen locations in the game
		for (int key : finalEdits.keySet()) {
			game.editState(getX(key), getY(key), finalEdits.get(key));
		}
		return !finalEdits.isEmpty();

	}

	private static boolean OnlyOneOptionRow(SudokuGame game, HashMap<Integer, HashSet<Integer>> possibilities) {
		int[][] state = game.getState();
		HashMap<Integer, Integer> finalEdits = new HashMap<Integer, Integer>();
		ArrayList<Integer> row = new ArrayList<Integer>();
		Integer[] locations = new Integer[9];
		int[] freq = new int[9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				//collect rows
				if (state[j][i] == 0) {
					for (Integer key : possibilities.keySet()) {
						if (key == getKey(j, i)) {
							row.add(key);
							break;
						}
					}
				}
			}
			// check for singular possibility in row
			for (Integer c : row) {
				for (int v : possibilities.get(c)) {
					freq[v - 1]++;
					locations[v - 1] = c;
				}
			}
			for (int f = 0; f < 9; f++) {
				if (freq[f] == 1) {
					finalEdits.put(locations[f], f + 1);
					// if we found the value, no need to iterate through it
					// when searching for Integers in the following rows
					possibilities.remove(locations[f]);
				}
			}
			//reset row
			row = new ArrayList<Integer>();		
		}
		// edit all of the chosen locations in the game
		for (Integer key : finalEdits.keySet()) {
			game.editState(getX(key), getY(key), finalEdits.get(key));
		}
		return !finalEdits.isEmpty();
	}
	
	private static boolean OnlyOneOptionCol(SudokuGame game, HashMap<Integer, HashSet<Integer>> possibilities) {
		int[][] state = game.getState();
		HashMap<Integer, Integer> finalEdits = new HashMap<Integer, Integer>();
		ArrayList<Integer> col = new ArrayList<Integer>();
		Integer[] locations = new Integer[9];
		int[] freq = new int[9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				//collect open Integers in column
				if (state[j][i] == 0) {
					for (Integer key : possibilities.keySet()) {
						if (key == getKey(i, j)) {
							col.add(key);
							break;
						}
					}
				}
			}
			// check for singular possibility in column
			for (Integer key : col) {
				for (int v : possibilities.get(key)) {
					freq[v - 1]++;
					locations[v - 1] = key;
				}
			}
			for (int f = 0; f < 9; f++) {
				if (freq[f] == 1) {
					finalEdits.put(locations[f], f + 1);
					// if we found the value, no need to iterate through it
					// when searching for Integers in the following columns
					possibilities.remove(locations[f]);
				}
			}
			//reset col
			col = new ArrayList<Integer>();		
		}
		// edit all of the chosen locations in the game
		for (Integer key : finalEdits.keySet()) {
			game.editState(getX(key), getY(key), finalEdits.get(key));
		}
		return !finalEdits.isEmpty();
	}
	
	//Maybe this should search and fix XY Splits 
	private static boolean XYSplit(SudokuGame game, HashMap<Integer, HashSet<Integer>> possibilities) {
		HashMap <Integer, Integer> foundSplits = new HashMap<Integer, Integer>();
		
		return false;
	}


}
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
	 * Method that updates the state until all coordinate positions are either
	 * filled, or the remaining coordinates have more than one possible solution.
	 * 
	 * @param game          The SudokuGame to be solved
	 * @param possibilities The possibility map for each position in the game.
	 * @return returns true if the state was updated and false if not.
	 */
	private static boolean updateState(SudokuGame game, HashMap<Coordinate, HashSet<Integer>> possibilities) {
		boolean updated = false;
		HashSet<Coordinate> trash = new HashSet<Coordinate>();
		// basic checks
		for (Coordinate c : possibilities.keySet()) {
			if (possibilities.get(c).size() == 1) {
				updated = true;
				game.editState(c.x, c.y, (int) possibilities.get(c).toArray()[0]);
				trash.add(c);
			}
		}
		for (Coordinate c : trash) {
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
	private static HashMap<Coordinate, HashSet<Integer>> getPossibleStates(SudokuGame game) {
		int[][] state = game.getState();
		HashMap<Coordinate, HashSet<Integer>> possibilityMap = new HashMap<Coordinate, HashSet<Integer>>();
		Coordinate location;
		HashSet<Integer> values;
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (state[y][x] == OPEN) {
					location = new Coordinate(x, y);
					values = getPossibleValues(game, location);
					possibilityMap.put(location, values);
				}
			}
		}
		return possibilityMap;
	}

	/**
	 * Method the gets the possible values at a given Coordinate
	 * 
	 * @param game The SudokuGame to be considered
	 * @param pos  The position in the SudokuGame state
	 * @return A HashSet of the possible chosen positions
	 */
	private static HashSet<Integer> getPossibleValues(SudokuGame game, Coordinate pos) {
		int[][] state = game.getState();
		int groupX = (pos.x / 3 == 0) ? 0 : (pos.x / 3 == 1) ? 3 : 6;
		int groupY = (pos.y / 3 == 0) ? 0 : (pos.y / 3 == 1) ? 3 : 6;
		HashSet<Integer> possibilities = new HashSet<Integer>();
		for (int i = 1; i < 10; i++) {
			possibilities.add(i);
		}
		for (int i = 0; i < 9; i++) {
			// check row
			if (possibilities.contains(state[pos.y][i])) {
				possibilities.remove(state[pos.y][i]);
			}
			// check column
			if (possibilities.contains(state[i][pos.y])) {
				possibilities.remove(state[i][pos.x]);
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
	
	private static boolean OnlyOneOption(SudokuGame game, HashMap<Coordinate, HashSet<Integer>> possibilities) {
		return (OnlyOneOptionRow(game, possibilities) || OnlyOneOptionCol(game, possibilities) || OnlyOneOptionGroup(game, possibilities));
	}
	

	private static boolean OnlyOneOptionGroup(SudokuGame game, HashMap<Coordinate, HashSet<Integer>> possibilities) {
		// if the possibility appears once in a group then that value is the possibility
		int[][] state = game.getState();
		HashMap<Coordinate, Integer> finalEdits = new HashMap<Coordinate, Integer>();
		ArrayList<Coordinate> group = new ArrayList<Coordinate>();
		Coordinate[] locations = new Coordinate[9];
		int[] freq = new int[9];
		for (int xOffset = 0; xOffset < 9; xOffset += 3) {
			for (int yOffset = 0; yOffset < 9; yOffset += 3) {
				// collect group
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (state[yOffset + j][xOffset + i] == 0) {
							for (Coordinate c : possibilities.keySet()) {
								if (c.isEqual(xOffset + i, yOffset + j)) {
									group.add(c);
									break;
								}
							}
						}
					}
				}
				// check for singular possibility in group
				for (Coordinate c : group) {
					for (int v : possibilities.get(c)) {
						freq[v - 1]++;
						locations[v - 1] = c;
					}
				}
				for (int f = 0; f < 9; f++) {
					if (freq[f] == 1) {
						finalEdits.put(locations[f], f + 1);
						// if we found the value, no need to iterate through it
						// when searching for Coordinates in the following groups
						possibilities.remove(locations[f]);
					}
				}

				// reset group
				group = new ArrayList<Coordinate>();
			}
		}
		// edit all of the chosen locations in the game
		for (Coordinate c : finalEdits.keySet()) {
			game.editState(c.x, c.y, finalEdits.get(c));
		}
		return !finalEdits.isEmpty();

	}

	private static boolean OnlyOneOptionRow(SudokuGame game, HashMap<Coordinate, HashSet<Integer>> possibilities) {
		int[][] state = game.getState();
		HashMap<Coordinate, Integer> finalEdits = new HashMap<Coordinate, Integer>();
		ArrayList<Coordinate> row = new ArrayList<Coordinate>();
		Coordinate[] locations = new Coordinate[9];
		int[] freq = new int[9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				//collect rows
				if (state[j][i] == 0) {
					for (Coordinate c : possibilities.keySet()) {
						if (c.isEqual(j, i)) {
							row.add(c);
							break;
						}
					}
				}
			}
			// check for singular possibility in row
			for (Coordinate c : row) {
				for (int v : possibilities.get(c)) {
					freq[v - 1]++;
					locations[v - 1] = c;
				}
			}
			for (int f = 0; f < 9; f++) {
				if (freq[f] == 1) {
					finalEdits.put(locations[f], f + 1);
					// if we found the value, no need to iterate through it
					// when searching for Coordinates in the following rows
					possibilities.remove(locations[f]);
				}
			}
			//reset row
			row = new ArrayList<Coordinate>();		
		}
		// edit all of the chosen locations in the game
		for (Coordinate c : finalEdits.keySet()) {
			game.editState(c.x, c.y, finalEdits.get(c));
		}
		return !finalEdits.isEmpty();
	}
	
	private static boolean OnlyOneOptionCol(SudokuGame game, HashMap<Coordinate, HashSet<Integer>> possibilities) {
		int[][] state = game.getState();
		HashMap<Coordinate, Integer> finalEdits = new HashMap<Coordinate, Integer>();
		ArrayList<Coordinate> col = new ArrayList<Coordinate>();
		Coordinate[] locations = new Coordinate[9];
		int[] freq = new int[9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				//collect open coordinates in column
				if (state[j][i] == 0) {
					for (Coordinate c : possibilities.keySet()) {
						if (c.isEqual(i, j)) {
							col.add(c);
							break;
						}
					}
				}
			}
			// check for singular possibility in column
			for (Coordinate c : col) {
				for (int v : possibilities.get(c)) {
					freq[v - 1]++;
					locations[v - 1] = c;
				}
			}
			for (int f = 0; f < 9; f++) {
				if (freq[f] == 1) {
					finalEdits.put(locations[f], f + 1);
					// if we found the value, no need to iterate through it
					// when searching for Coordinates in the following columns
					possibilities.remove(locations[f]);
				}
			}
			//reset col
			col = new ArrayList<Coordinate>();		
		}
		// edit all of the chosen locations in the game
		for (Coordinate c : finalEdits.keySet()) {
			game.editState(c.x, c.y, finalEdits.get(c));
		}
		return !finalEdits.isEmpty();
	}
	
	//Maybe this should search and fix XY Splits 
	private static boolean XYSplit(SudokuGame game, HashMap<Coordinate, HashSet<Integer>> possibilities) {
		// Instantiate found x-y split collection as a HashMap with the earliest coordinate first (see Coordinate inner class)
		HashMap <Coordinate, Coordinate> foundSplits = new HashMap<Coordinate, Coordinate>();
//		ArrayList<Coordinate> currentSeachSpace;
//		// Collect Search locations: groups, columns, rows
//		//just go in order 1-9 is rows 1-9/ 10-18 is columns 1-9/ 19-27 is groups 1-9
//		for (int i = 1; i <= 27; i++) {
////			Set the currentSearchspace
//			if (i < 10) {
//				// collect the rows
//			} else if (i < 19) {
//				// collect the columns
//			} else {
//				// collect the groups
//			}
//			
//		}
//		
//		
//		// Look for xy split from each location (helper method) detect XY Split returns the 2 coordinates(earliest first)
//		
		
//		for each coordinate in the HashMap, can we look for an x-y split? n
		
//		instead of a HashMap of coordinates and their possibilities, can we do a 2d array of possibilities, 
		
//		A 2-D ARRAY OF HASHSETS!!!!!!! That way we save space and wont need the coordinate class, and each possibility 
//		could be accessed randomly. 
		
		// For an xy split found 
			// Remove x and y from other possibilities in current location
			// Remove all other possibilities from the x-y split's 2 coordinates
			// Add to a collection of found x-y splits so it is not repeated (earliest first)
		// Search for other splits until all locations have been searched
		// If the collection of found x-y splits is empty, then return false, otherwise return true. use a ternary operator for brevity
		
		
		return false;
	}
	

	private static class Coordinate {
		
		// x is the row
		// y is the column
		public int x;
		public int y;

		Coordinate(int X, int Y) {
			this.x = X;
			this.y = Y;
		}

		private boolean isEqual(int X, int Y) {
			return (this.x == X && this.y == Y) ? true : false;
		}
		//Earliest in this case is defined as smallest row, then smallest column.
		//Returns earliest coordinate or null(if they are the same).
		private static Coordinate earliestCoordinate( Coordinate one, Coordinate two) {
			if (one.x < two.x) {
				return one;
			} else if (two.x < one.x) {
				return two;
			} else if (one.y < two.y) {
				return one;
			} else if (two.y < one.y) {
				return two;
			}
			return null;
		}
	}

}
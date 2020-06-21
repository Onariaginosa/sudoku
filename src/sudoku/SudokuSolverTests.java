/**
 * 
 */
package sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author onariaginosa
 *
 */
class SudokuSolverTests {

	/**
	 * @throws java.lang.Exception
	 */
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}

	/**
	 * @throws java.lang.Exception
	 */
//	@BeforeEach
//	void setUp() throws Exception {
//	}

	/**
	 * Test method for {@link sudoku.SudokuSolver#solver(sudoku.SudokuGame)}.
	 */
	@Test
	void testSolverBasic() {
//		completed is the correct response
		int[][] completed = {
				{1,2,3,4,5,6,7,8,9},
				{4,5,6,7,8,9,1,2,3},
				{7,8,9,1,2,3,4,5,6},
				{2,3,4,5,6,7,8,9,1},
				{5,6,7,8,9,1,2,3,4},
				{8,9,1,2,3,4,5,6,7},
				{3,4,5,6,7,8,9,1,2},
				{6,7,8,9,1,2,3,4,5},
				{9,1,2,3,4,5,6,7,8}
		};
		
		int[][] OneMoveAway = {
				{1,2,3,4,5,6,7,8,9},
				{4,5,6,7,8,9,1,2,3},
				{7,8,9,1,2,3,4,5,6},
				{2,3,4,5,6,7,8,9,1},
				{5,6,7,8,9,1,0,3,4},
				{8,9,1,2,3,4,5,6,7},
				{3,4,5,6,7,8,9,1,2},
				{6,7,8,9,1,2,3,4,5},
				{9,1,2,3,4,5,6,7,8}
		};
		int[][] TwoMovesAway = {
				{1,2,3,4,5,6,7,8,9},
				{4,5,6,7,8,9,1,2,3},
				{7,8,9,1,2,3,4,5,6},
				{2,3,4,5,6,7,8,9,1},
				{5,6,7,8,9,1,0,3,4},
				{8,9,1,2,3,4,5,6,7},
				{3,0,5,6,7,8,9,1,2},
				{6,7,8,9,1,2,3,4,5},
				{9,1,2,3,4,5,6,7,8}
		};
		int[][] ThreeMovesAway = {
				{1,2,3,4,5,6,7,8,9},
				{4,5,0,7,8,9,1,2,3},
				{7,8,9,1,2,3,4,5,6},
				{2,3,4,5,6,7,8,9,1},
				{5,6,7,8,9,1,0,3,4},
				{8,9,1,2,3,4,5,6,7},
				{3,0,5,6,7,8,9,1,2},
				{6,7,8,9,1,2,3,4,5},
				{9,1,2,3,4,5,6,7,8}
		};
		int[][] FourMovesAway = {
				{1,2,3,4,5,6,7,8,9},
				{4,5,0,7,8,9,1,2,3},
				{7,8,9,1,2,3,4,5,6},
				{2,3,4,5,6,7,8,9,1},
				{5,6,7,8,9,1,0,3,4},
				{8,9,1,2,3,4,5,6,7},
				{3,0,5,6,7,8,9,1,2},
				{6,7,8,9,1,2,3,4,5},
				{9,1,2,3,4,5,6,7,8}
		};
		
		SudokuGame oneMove = new SudokuGame(OneMoveAway);
		SudokuGame twoMoves = new SudokuGame(TwoMovesAway);
		SudokuGame threeMoves = new SudokuGame(ThreeMovesAway);
		SudokuGame fourMoves = new SudokuGame(FourMovesAway);
		
		SudokuSolver.solver(oneMove);
		assertEquals(true, oneMove.isStateEqual(completed));
		SudokuSolver.solver(twoMoves);
		assertEquals(true, twoMoves.isStateEqual(completed));
		SudokuSolver.solver(threeMoves);
		assertEquals(true, threeMoves.isStateEqual(completed));
		SudokuSolver.solver(fourMoves);
		assertEquals(true, fourMoves.isStateEqual(completed));
		
	}
	
	@Test
	void testSolverEasyModeOnline() {
		int[][] easy1 = {
				{4,8,9,5,0,1,0,2,0},
				{7,5,0,0,0,0,8,1,0},
				{0,0,0,0,2,0,5,9,4},
				{0,0,8,0,9,0,0,7,5},
				{5,0,0,0,0,8,0,0,0},
				{0,0,1,0,0,3,0,0,0},
				{1,6,0,3,7,4,0,8,2},
				{0,0,0,0,0,5,7,3,6},
				{0,0,3,0,6,2,4,5,0}
		};
		int[][] easy1Duplicate = {
				{4,8,9,5,0,1,0,2,0},
				{7,5,0,0,0,0,8,1,0},
				{0,0,0,0,2,0,5,9,4},
				{0,0,8,0,9,0,0,7,5},
				{5,0,0,0,0,8,0,0,0},
				{0,0,1,0,0,3,0,0,0},
				{1,6,0,3,7,4,0,8,2},
				{0,0,0,0,0,5,7,3,6},
				{0,0,3,0,6,2,4,5,0}
		};
		int[][] easy1Solution = {
				{4,8,9,5,3,1,6,2,7},
				{7,5,2,6,4,9,8,1,3},
				{3,1,6,8,2,7,5,9,4},
				{2,3,8,4,9,6,1,7,5},
				{5,4,7,2,1,8,3,6,9},
				{6,9,1,7,5,3,2,4,8},
				{1,6,5,3,7,4,9,8,2},
				{9,2,4,1,8,5,7,3,6},
				{8,7,3,9,6,2,4,5,1}
		};
		
		SudokuGame easy1Game = new SudokuGame(easy1);
		SudokuSolver.solver(easy1Game);
		assertEquals(false, easy1Game.isStateEqual(easy1Duplicate));
		assertEquals(true, easy1Game.isStateEqual(easy1Solution));
		
		
		
	}

}

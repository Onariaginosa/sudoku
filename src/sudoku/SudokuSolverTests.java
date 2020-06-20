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
	void testSolverEasyMode() {
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
		
		assertEquals(true, oneMove.isStateEqual(completed));
		assertEquals(true, twoMoves.isStateEqual(completed));
		assertEquals(true, threeMoves.isStateEqual(completed));
		assertEquals(true, fourMoves.isStateEqual(completed));
	}

}

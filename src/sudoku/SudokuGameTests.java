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
class SudokuGameTests {

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
	 * Test method for {@link sudoku.SudokuGame#SudokuGame(int[][])}.
	 * Constructor/IsValid Game Tests 
	 */
	@Test
	void testSudokuGame() {
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
		int[][] inProgress = {
				{1,2,3,4,5,6,7,8,9},
				{4,5,6,7,8,9,1,0,3},
				{7,8,9,1,2,3,4,5,6},
				{2,3,4,5,0,7,8,9,1},
				{5,6,7,8,9,1,2,0,4},
				{8,9,1,2,3,4,5,6,7},
				{3,0,5,6,7,8,9,1,2},
				{6,7,8,9,1,2,3,4,5},
				{9,1,2,3,4,5,6,7,8}
		};
		int[][] outOfRangeComplete = {
				{1,2,3,4,5,6,7,8,9},
				{4,5,6,7,8,9,1,2,3},
				{7,8,9,1,2,3,4,5,6},
				{2,3,4,5,64,7,8,9,1},
				{5,6,7,8,9,1,2,3,4},
				{8,9,1,2,3,4,5,6,7},
				{3,10,5,6,7,8,9,-1,2},
				{6,7,8,9,1,2,3,4,5},
				{9,1,2,3,4,5,6,7,8}
		};
		int[][] outOfRangeIncomplete = {
				{1,2,3,4,5,6,7,8,9},
				{4,5,6,7,8,9,1,2,3},
				{7,8,9,1,2,3,4,5,6},
				{2,3,4,5,64,7,8,9,1},
				{5,6,7,8,9,1,2,3,4},
				{8,9,1,2,3,4,5,6,7},
				{3,10,5,6,7,8,9,-1,2},
				{6,7,8,9,0,2,3,4,5},
				{9,1,2,3,4,5,6,7,8}
		};
		int[][] missingRows = {
				{4,5,6,7,8,9,1,2,3},
				{7,8,9,1,2,3,4,5,6},
				{2,3,4,5,6,7,8,9,1},
				{5,6,7,8,9,1,2,3,4},
				{8,9,1,2,3,4,5,6,7},
				{3,4,5,6,7,8,9,1,2},
				{6,7,8,9,1,2,3,4,5},
				{9,1,2,3,4,5,6,7,8}
		};
		int[][] extraRows = {
				{4,5,6,7,8,9,1,2,3},
				{7,8,9,1,2,3,4,5,6},
				{2,3,4,5,6,7,8,9,1},
				{5,6,7,8,9,1,2,3,4},
				{8,9,1,2,3,4,5,6,7},
				{3,4,5,6,7,8,9,1,2},
				{6,7,8,9,1,2,3,4,5},
				{9,1,2,3,4,5,6,7,8},
				{4,5,6,7,8,9,1,2,3},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
		};
		int[][] missingColumns = {
				{1,2,3,4,5,6,7,8},
				{4,5,6,7,8,9,1,2},
				{7,8,9,1,2,3,4,5},
				{2,3,4,5,6,7,8,9},
				{5,6,7,8,9,1,2,3},
				{8,9,1,2,3,4,5,6},
				{3,4,5,6,7,8,9,1},
				{6,7,8,9,1,2,3,4},
				{9,1,2,3,4,5,6,7}
		};
		int[][] extraColumns = {
				{1,2,3,4,5,6,7,8,9,0},
				{4,5,6,7,8,9,1,2,3,0},
				{7,8,9,1,2,3,4,5,6,0},
				{2,3,4,5,6,7,8,9,1,0},
				{5,6,7,8,9,1,2,3,4,0},
				{8,9,1,2,3,4,5,6,7,0},
				{3,4,5,6,7,8,9,1,2,0},
				{6,7,8,9,1,2,3,4,5,0},
				{9,1,2,3,4,5,6,7,8,0}
		};
		int[][] missingColumnsExtraRows = {
				{1,2,3,4,5,6,7,8},
				{4,5,6,7,8,9,1,2},
				{7,8,9,1,2,3,4,5},
				{2,3,4,5,6,7,8,9},
				{5,6,7,8,9,1,2,3},
				{8,9,1,2,3,4,5,6},
				{3,4,5,6,7,8,9,1},
				{6,7,8,9,1,2,3,4},
				{9,1,2,3,4,5,6,7},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0}
		};
		int[][] missingColumnsMissingRows = {
				{1,2,3,4,5,6,7,8},
				{4,5,6,7,8,9,1,2},
				{7,8,9,1,2,3,4,5},
				{2,3,4,5,6,7,8,9},
				{5,6,7,8,9,1,2,3},
				{8,9,1,2,3,4,5,6}
		};
		int[][] extraColumnsExtraRows = {
				{1,2,3,4,5,6,7,8,9,0},
				{4,5,6,7,8,9,1,2,3,0},
				{7,8,9,1,2,3,4,5,6,0},
				{2,3,4,5,6,7,8,9,1,0},
				{5,6,7,8,9,1,2,3,4,0},
				{8,9,1,2,3,4,5,6,7,0},
				{3,4,5,6,7,8,9,1,2,0},
				{6,7,8,9,1,2,3,4,5,0},
				{9,1,2,3,4,5,6,7,8,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
		};
		int[][] extraColumnsMissingRows = {
				{1,2,3,4,5,6,7,8,9,0},
				{4,5,6,7,8,9,1,2,3,0},
				{7,8,9,1,2,3,4,5,6,0},
				{2,3,4,5,6,7,8,9,1,0},
				{5,6,7,8,9,1,2,3,4,0},
				{8,9,1,2,3,4,5,6,7,0}
		};
		
		SudokuGame test1 = new SudokuGame(completed);
		SudokuGame test2 = new SudokuGame(inProgress);
		SudokuGame test3 = new SudokuGame(outOfRangeComplete);
		SudokuGame test4 = new SudokuGame(outOfRangeIncomplete);
		SudokuGame test5 = new SudokuGame(missingRows);
		SudokuGame test6 = new SudokuGame(extraRows);
		SudokuGame test7 = new SudokuGame(missingColumns);
		SudokuGame test8 = new SudokuGame(extraColumns);
		SudokuGame test9 = new SudokuGame(missingColumnsExtraRows);
		SudokuGame test10 = new SudokuGame(missingColumnsMissingRows);
		SudokuGame test11 = new SudokuGame(extraColumnsExtraRows);
		SudokuGame test12 = new SudokuGame(extraColumnsMissingRows);
		assertEquals(completed, test1.state);
		assertEquals(inProgress, test2.state);
		assertEquals(null, test3.state);
		assertEquals(null, test4.state);
		assertEquals(null, test5.state);
		assertEquals(null, test6.state);
		assertEquals(null, test7.state);
		assertEquals(null, test8.state);
		assertEquals(null, test9.state);
		assertEquals(null, test10.state);
		assertEquals(null, test11.state);
		assertEquals(null, test12.state);
	}

	/**
	 * Test method for {@link sudoku.SudokuGame#editState(int, int, int)}.
	 */
	@Test
	void testEditState() {
		int[][] start = {
				{0,0,3,0,5,0,7,8,9},
				{4,5,6,7,8,9,1,2,3},
				{7,8,9,1,2,3,4,5,6},
				{2,3,0,5,6,7,8,9,1},
				{5,6,7,8,9,1,2,3,4},
				{8,9,1,2,0,0,5,6,7},
				{3,4,5,6,7,8,9,1,2},
				{6,7,8,9,1,2,3,4,5},
				{9,1,2,3,4,5,6,7,8}
		};
		int[][] set00to1 = {
				{1,0,3,0,5,0,7,8,9},
				{4,5,6,7,8,9,1,2,3},
				{7,8,9,1,2,3,4,5,6},
				{2,3,0,5,6,7,8,9,1},
				{5,6,7,8,9,1,2,3,4},
				{8,9,1,2,0,0,5,6,7},
				{3,4,5,6,7,8,9,1,2},
				{6,7,8,9,1,2,3,4,5},
				{9,1,2,3,4,5,6,7,8}
		};
		int[][] set45to3 = {
				{1,0,3,0,5,0,7,8,9},
				{4,5,6,7,8,9,1,2,3},
				{7,8,9,1,2,3,4,5,6},
				{2,3,0,5,6,7,8,9,1},
				{5,6,7,8,9,1,2,3,4},
				{8,9,1,2,3,0,5,6,7},
				{3,4,5,6,7,8,9,1,2},
				{6,7,8,9,1,2,3,4,5},
				{9,1,2,3,4,5,6,7,8}
		};
		
		SudokuGame game = new SudokuGame(start);
		game.editState(0, 0, 1);
		assertEquals(true, game.isStateEqual(set00to1));
		game.editState(4, 5, 3);
		assertEquals(true, game.isStateEqual(set45to3));
	}

	/**
	 * Test method for {@link sudoku.SudokuGame#isComplete()}.
	 */
	@Test
	void testIsComplete() {
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
		int[][] inProgress = {
				{1,2,3,4,5,6,7,8,9},
				{4,5,6,7,8,9,1,0,3},
				{7,8,9,1,2,3,4,5,6},
				{2,3,4,5,0,7,8,9,1},
				{5,6,7,8,9,1,2,0,4},
				{8,9,1,2,3,4,5,6,7},
				{3,0,5,6,7,8,9,1,2},
				{6,7,8,9,1,2,3,4,5},
				{9,1,2,3,4,5,6,7,8}
		};
		SudokuGame completeGame = new SudokuGame(completed);
		SudokuGame incompleteGame = new SudokuGame(inProgress);
		
		assertEquals(true, completeGame.isComplete());
		assertEquals(false, incompleteGame.isComplete());
		
	}

	/**
	 * Test method for {@link sudoku.SudokuGame#isWin()}.
	 */
	@Test
	void testIsWin() {
		int[][] Win1 = {
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
		int[][] Win2 = {
				{2,3,4,5,6,7,8,9,1},
				{5,6,7,8,9,1,2,3,4},
				{8,9,1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7,8,9},
				{4,5,6,7,8,9,1,2,3},
				{7,8,9,1,2,3,4,5,6},
				{3,4,5,6,7,8,9,1,2},
				{6,7,8,9,1,2,3,4,5},
				{9,1,2,3,4,5,6,7,8}
		};
		int[][] incompleteLoss = {
				{1,2,3,4,5,6,7,8,9},
				{4,5,6,7,8,9,1,0,3},
				{7,8,9,1,2,3,4,5,6},
				{2,3,4,5,0,7,8,9,1},
				{5,6,7,8,9,1,2,0,4},
				{8,9,1,2,3,4,5,6,7},
				{3,0,5,6,7,8,9,1,2},
				{6,7,8,9,1,2,3,4,5},
				{9,1,2,3,4,5,6,7,8}
		};
		
		SudokuGame isWin1 = new SudokuGame(Win1);
		SudokuGame isWin2 = new SudokuGame(Win2);
		SudokuGame isIncompleteLoss = new SudokuGame(incompleteLoss);
		SudokuGame isIncorrectLoss = new SudokuGame(Win2);
		
		assertEquals(true, isWin1.isWin());
		assertEquals(true, isWin2.isWin());
		assertEquals(false, isIncompleteLoss.isWin());
		isIncorrectLoss.editState(0, 0, 1);
		assertEquals(false, isIncorrectLoss.isWin());
	}

}

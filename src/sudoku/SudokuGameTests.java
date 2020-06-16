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
		
		SudokuGame test1 = new SudokuGame(completed);
		SudokuGame test2 = new SudokuGame(inProgress);
		SudokuGame test3 = new SudokuGame(completed);
		SudokuGame test4 = new SudokuGame(outOfRangeComplete);
		SudokuGame test5 = new SudokuGame(outOfRangeIncomplete);
		SudokuGame test6 = new SudokuGame(missingRows);
		SudokuGame test7 = new SudokuGame(extraRows);
		SudokuGame test8 = new SudokuGame(missingColumns);
		
		assertEquals(completed, test1.state);
		assertEquals(null, test2.state);
		assertEquals(null, test3.state);
		assertEquals(null, test4.state);
		assertEquals(null, test5.state);
		assertEquals(null, test6.state);
		assertEquals(null, test7.state);
		assertEquals(null, test8.state);
		assertEquals(null, test9.state);
	}

	/**
	 * Test method for {@link sudoku.SudokuGame#isValidGame(int[][])}.
	 */
	@Test
	void testIsValidGame() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link sudoku.SudokuGame#editState(int, int, int)}.
	 */
	@Test
	void testEditState() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link sudoku.SudokuGame#isComplete()}.
	 */
	@Test
	void testIsComplete() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link sudoku.SudokuGame#isWin()}.
	 */
	@Test
	void testIsWin() {
		fail("Not yet implemented"); // TODO
	}

}

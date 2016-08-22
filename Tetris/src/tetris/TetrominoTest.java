package tetris;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TetrominoTest {
	
	
	
	private Object iRotations;
	private ArrayList<Tetromino> tRotations;

	// Function to see if two tetromino are equal
	// tetromino a is what tetromino b is compared against
	// Commenting out all the feedback
	private boolean areTetrominosEqual(Tetromino a, Tetromino b){
		
		boolean[][] aGrid = a.getShapeGrid();
		boolean[][] bGrid = b.getShapeGrid();

		int gridLength = aGrid.length;
		// System.out.println("grid length: " +gridLength);

		
		// check that the grids have the same length
		if (gridLength != bGrid.length) {
			// System.out.println("grid lengths !=");
			return false;
		}
		
		// check that the tetrominos have the same location
		// System.out.println("X coordinates a, b: " +a.getXLocation() +", " +b.getXLocation());
		// System.out.println("Y coordinates a, b: " +a.getYLocation() +", " +b.getYLocation());
		
		if (a.getXLocation() != b.getXLocation()) {
			// System.out.println("X coordinates don't match");
			return false;
		}
		if (a.getYLocation() != b.getYLocation()) {
			// System.out.println("Y coordinates don't match");
			return false;
		}

		// Print out the Tetrominos to be compared
		// System.out.println("a Grid:");
		// printGrid(aGrid);
		// System.out.println("b Grid");
		// printGrid(bGrid);

		// check that all of the trominos match
		for(int i=0; i < gridLength; i++) // rows
		{
			for(int j=0; j< gridLength; j++) // columns
			{
				if (aGrid[i][j] != bGrid[i][j]) {
					// System.out.println("Grid point" +i +"," +j +" doesn't match");
					return false;
				};
			}
		}
		
		// tetromino a == tetromino b
		// System.out.println("Tetrominos match");
		return true;
	}
		
	// function to print out a Tetromino grid: 
	// Assumes a square grid.
	// copied from TetrominoTester by Tatiana Petkova
		public static void printGrid(boolean[][] testerGrid){
			for( int i = 0; i<testerGrid.length; i++){
				for (int j = 0; j<testerGrid[i].length; j++){
					if ( testerGrid[i][j] ){
						System.out.print("T");
					}
					else
						System.out.print(" ");
				}
				System.out.println("");
			}
		}


	@Before
	public void setUp() throws Exception {
	}
	
	
	// Test to verify that function to compare Tetrominos are the same
	@Test
	public void testAreTetrominosEqual() {
		
		Tetromino alpha = new Tetromino('t');
		Tetromino beta = new Tetromino('j');
		boolean sameTromino = false;
		
		// System.out.println("testing that alpha == alpha");
		sameTromino = areTetrominosEqual(alpha, alpha); // Should return true.
		assertTrue(sameTromino);
		
		// System.out.println("testing that alpha != beta");
		sameTromino = areTetrominosEqual(alpha, beta); // Should return false.
		assertFalse(sameTromino);

	}

	
	@Test
	public void testRotate() {
		boolean T = true;
		boolean F = false;

		ArrayList<Tetromino> iRotations;
		this.iRotations = new ArrayList<Tetromino>();
		for (int i = 0; i < 4; i++) {
			((ArrayList<Tetromino>) this.iRotations).add(new Tetromino('i'));
		}

		
		
		ArrayList<Tetromino> tRotations;
		this.tRotations = new ArrayList<Tetromino>();
		for (int i = 0; i < 4; i++) {
			((ArrayList<Tetromino>) this.tRotations).add(new Tetromino('t'));
		}

	}

	@Test
	public void testSetLocation() {
		fail("Not yet implemented");
	}


	
 /*	tetromino() is a creator that is validated in the testAreTetrominosEqual function
 * 	and therefore doesn't need a separate test.
 *	@Test
 *	public void testTetromino() {
 *		fail("Not yet implemented");
 *	}
 */

 /*	getShapeGrid() is a getter that is validated in the areTetrominosEqual function
 * 	and therefore doesn't need a separate test.
 *	@Test
 *	public void testGetShapeGrid() {
 *		fail("Not yet implemented");
 *	}
 */

	
 /*	getXLocation() is a getter that is validated in the areTetrominosEqual function
 * 	and therefore doesn't need a separate test.
 * 	@Test
 *	public void testGetXLocation() {
 *		fail("Not yet implemented");
 *	}
 */

 /*	getYLocation() is a getter that is validated in the areTetrominosEqual function
 * 	and therefore doesn't need a separate test.
 *	@Test
 *	public void testGetYLocation() {
 *		fail("Not yet implemented");
 *	}
 */
}

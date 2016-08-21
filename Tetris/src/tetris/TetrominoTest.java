package tetris;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TetrominoTest {
	
	
	
	// Function to see if two tetromino are equal
	private boolean tetrominoEqual(Tetromino a, Tetromino b){
		
		boolean[][] aGrid = a.getShapeGrid();
		boolean[][] bGrid = b.getShapeGrid();

		int gridLength = aGrid.length;
		
		if (a.getXLocation() != b.getXLocation()) {
			return false;
		};
		if (a.getYLocation() != b.getYLocation()){
			return false;
		};
		for(int i=0; i < gridLength; i++) {
			for(int j=0; j< gridLength; j++) {
				if (aGrid[i][j] != bGrid[i][j]) {
					return false;
				};
			}
		}
		return true;
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTetromino() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetShapeGrid() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetXLocation() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetYLocation() {
		fail("Not yet implemented");
	}

	@Test
	public void testRotate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLocation() {
		fail("Not yet implemented");
	}

}

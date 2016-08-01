package tetris;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class for holding and managing individual game components
 * 
 * This class handles the main data & logic of the game, including
 * collision detection, sending movement & rotation instructions, 
 * deletion of rows, score calculation,
 * tetromino creation and management
 * 
 * These all interact to form the "state" of the game
 * 
 */
public class GameSystem {
	private int score;
	private ArrayList<Tetromino> nextShapeQueue;
	private Tetromino activeTetromino;
	
	private boolean blockGrid[][];
	
	private static char[] shapeChoices = {'i', 'o', 's', 't', 'z', 'j', 'l'};
	private static int HEIGHT = 22;
	private static int WIDTH = 10;
	
	/**
	 * Default constructor for game system
	 * 
	 * @return game system object
	 * 
	 */
	public GameSystem(){
		// Set score to zero
		this.score = 0;
		
		// Game board is 22 blocks high by 10 blocks wide
		this.blockGrid = new boolean[HEIGHT][WIDTH];
		
		// Initialize Tetrominos
		this.nextShapeQueue = new ArrayList<Tetromino>();
		for (int i = 0; i < 3; i++)
		{
			nextShapeQueue.add(createRandomTetromino());
		}
	}
	
	/**
	 * Get grid for runner use in display
	 * @return blockGrid
	 */
	public boolean[][] getGrid(){
		return this.blockGrid;
	}
	
	/**
	 * Get score for runner use in display
	 * @return score
	 */
	public int getScore(){
		return this.score;
	}
	
	//Tetromino Functionality
	/**
	 * Creates a random-shaped tetromino (of the shape options)
	 * Returns that tetromino
	 */
	static Tetromino createRandomTetromino(){
		Random rand = new Random();
		
		return new Tetromino(shapeChoices[rand.nextInt(shapeChoices.length)]);
	}
	
	/**
	 * Finds active tetromino and moves it
	 * 
	 * @param x_direction
	 * @param y_direction
	 * @param rotation
	 * 
	 * x = -1 means move left, x = 1 means move right
	 * y = -1 means move up, y = 1 means move down
	 * rotation = 1 means rotate right/clockwise 90 degrees
	 * rotation = -1 means rotate left/counter-clockwise 90 degrees
	 * 
	 */
	public void moveActiveTetromino(int x_direction, int y_direction, int rotation){
		boolean[][] tempGrid;
		int x_loc;
		int y_loc;
		int i;
		int j;
		
		// Rotation handling
		if (rotation != 0)
		{
			this.activeTetromino.rotate(rotation);
			tempGrid = this.activeTetromino.getShapeGrid();
			x_loc = this.activeTetromino.getXLocation();
			y_loc = this.activeTetromino.getYLocation();
			
			// Check for collision and revert if collision occurred
			for (i = 0; i < tempGrid.length; i++) //rows
			{
				for (j = 0; j < tempGrid[0].length; j++) //columns
				{
					// Out of bounds to the left
					if (tempGrid[i][j] && (j+x_loc < 0))
					{
						this.activeTetromino.rotate(rotation*-1);;
						return;
					}
					
					// Out of bounds to the right
					if (tempGrid[i][j] && (j+x_loc > WIDTH))
					{
						this.activeTetromino.rotate(rotation*-1);;
						return;
					}
					
					// Upward/other overlap
					if (tempGrid[i][j] && this.blockGrid[i+y_loc][j+x_loc])
					{
						// Collision; revert rotation
						this.activeTetromino.rotate(rotation*-1);;
						return;
					}
				}
			}
			
			// No collisions detected; retain rotation
			return;
		}
		
		// Normal movement handling
		tempGrid = this.activeTetromino.getShapeGrid();
		x_loc = this.activeTetromino.getXLocation();
		y_loc = this.activeTetromino.getYLocation();
		
		// If moving up, check that we do not collide upwards
		if (y_direction == -1)
		{
			// TODO: what if grid goes farther up than 0
			
			i = 0; // check along the topmost row
			for (j = 0; j < tempGrid[0].length; j++)
			{
				if (i + y_loc == 0) // tetromino grid aligns with the top
				{
					if	(tempGrid[i][j]) // we have a block which has reached the top
					{
						// TODO: land shape, complete rows, remove active tetromino
						return;
					}
				}
				else if (i + y_loc > 0) // can only collide if there is something at the top to collide with
				{
					// IF there are two blocks vertically on top of one another
					if (tempGrid[i][j] && this.blockGrid[i + y_loc - 1][j + x_loc]) 
					{
						// Collision imminent
						// TODO: land shape, complete rows, remove active tetromino
						return;
					}
				}
			}	
			
			// Cleared upward collision check
		}
		else if (x_direction == -1) // If moving left
		{
			// TODO: what if grid overlaps to -1
			
			// check that we do not collide leftward
			j = 0; // check along the leftmost column
			for (i = 0; i < tempGrid.length; i++)
			{
				if (j + x_loc == 0) // grid aligns with the left
				{
					if (tempGrid[i][j]) // we have a block which is on the left wall
					{
						// Do not move
						return; 
					}
				}
				else if (j + x_loc > 0)
				{
					if (tempGrid[i][j] && this.blockGrid[i + y_loc][j + x_loc - 1])
					{
						// Collision imminent; do not move
						return;
					}
				}
			}
		}
		else if (x_direction == 1) // If moving right
		{
			// TODO: what if grid overlaps to more than width
			
			// check that we do not collide rightward
			j = tempGrid[0].length - 1; // check along the rightmost column
			for (i = 0; i < tempGrid.length; i++)
			{
				if ((j + x_loc) == (WIDTH - 1)) // grid aligns with the right
				{
					if (tempGrid[i][j]) // we have a grid on the right wall
					{
						// Do not move
						return;
					}
				}
				else if ((j + x_loc) < (WIDTH - 1))
				{
					if (tempGrid[i][j] && this.blockGrid[i + y_loc][j + x_loc + 1])
					{
						// Collision imminent; do not move
						return;
					}
				}
			}
		}
	
		// No collisions detected, move tetromino
		this.activeTetromino.setLocation(x_loc+x_direction, y_loc+y_direction);
		
		
	}
	
	/**
	 * Clean up active tetromino
	 */
	private void removeActiveTetromino(){
		this.activeTetromino = null;
	}
	
	/**
	 * Grab new active tetromino
	 */
	private void setActiveTetromino(){
		// Pop tetromino from queue
		// Hold it in variable
		this.activeTetromino = this.nextShapeQueue.remove(0);
	}
	
	/**
	 * Get active tetromino
	 * @return active tetromino
	 */
	private Tetromino getActiveTetromino(){
		return this.activeTetromino;
	}
	
	/**
	 * Release tetromino so it can begin moving
	 */
	private void releaseTetromino(){
		// Begin moving active tetromino through board
		
		// If tetromino cannot move down, game over
	}
	
	/** 
	 * land and lock shape to game grid
	 * increase score
	 */
	private void landShape(){
		
		// Get location of active tetromino
		
		// Get positions of each of its blocks
		
		// Incorporate these into the current grid
	}
	
	/**
	 * Check for completed rows and delete those that have been completed
	 * increase score
	 */
	static void completeRows(){
		
	}
	
}

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
	private static int SHAPE_LAND_SCORE = 50;
	
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
		
		if (this.activeTetromino == null || this.activeTetromino.getXLocation() == -111)
		{
			// Unreleased tetromino
			return;
		}
		
		if (rotation != 0)
		{
			// Handle rotation
			this.activeTetromino.rotate(rotation);
			
			
			tempGrid = this.activeTetromino.getShapeGrid();
			x_loc = this.activeTetromino.getXLocation();
			y_loc = this.activeTetromino.getYLocation();
			
			// Check for collision
			for (i = 0; i < tempGrid.length; i++) //rows
			{
				for (j = 0; j < tempGrid[0].length; j++) //columns
				{
					// Out of bounds to the left
					if (tempGrid[i][j] && (j+x_loc < 0))
					{
						// Undo rotation
						this.activeTetromino.rotate(rotation*-1);;
						return;
					}
					
					// Out of bounds to the right
					if (tempGrid[i][j] && (j+x_loc > WIDTH))
					{
						// Undo rotation
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
		}
		else 
		{
			// Normal movement handling
			tempGrid = this.activeTetromino.getShapeGrid();
			x_loc = this.activeTetromino.getXLocation();
			y_loc = this.activeTetromino.getYLocation();
			
			for (i = 0; i < tempGrid.length; i++) //rows
			{
				for (j = 0; j < tempGrid[0].length; j++) //columns
				{
					// Only check for collision if there is a block present
					if (tempGrid[i][j])
					{
						// Check for upward collision
						if (y_direction == -1)
						{
							if ((i+y_loc) <= 0)
							{
								// Top of board, stop
								landShape();
								return;
							}
							
							if (this.blockGrid[i+y_loc - 1][j+x_loc])
							{
								// Block immediately above, stop
								landShape();
								return;
							}
						}
						
						// Check for rightward collision
						
						else if (x_direction == 1)
						{
							if ((j+x_loc >= WIDTH))
							{
								// Up against the wall; don't move
								return;
							}
							
							if (this.blockGrid[i+y_loc][j+x_loc+1])
							{
								// Block immediately to the right; don't move
								return;
							}
						}
						
						// Check for leftward collision
						else if (x_direction == -1)
						{
							if((j+x_loc <= 0))
							{
								// Up against the wall; don't move
								return;
							}
							
							if (this.blockGrid[i+y_loc][j+x_loc-1])
							{
								// Block immediately to the left; don't move
								return;
							}
						}
					}
				}
			}
			
			// No collisions detected, move tetromino
			this.activeTetromino.setLocation(x_loc+x_direction, y_loc+y_direction);
		}
		
		// TODO: update display
		
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
		
		// If tetromino cannot move up, game over
	}
	
	/** 
	 * land and lock shape to game grid
	 * increase score
	 */
	private void landShape(){
		
		// Get location of active tetromino
		boolean[][] tempGrid = this.activeTetromino.getShapeGrid().clone();
		int x_loc = this.activeTetromino.getXLocation();
		int y_loc = this.activeTetromino.getYLocation();
		
		removeActiveTetromino();
		
		// Get positions of each of its blocks
		// Incorporate these into the current grid
		for (int i = 0; i < tempGrid.length; i++) //rows
		{
			for (int j = 0; j < tempGrid[0].length; j++) //columns
			{
				if (tempGrid[i][j])
				{
					this.blockGrid[i + y_loc][j + x_loc] = true;
				}
			}
		}
		
		this.score += SHAPE_LAND_SCORE;
		
		completeRows();
		
		setActiveTetromino();		
		releaseTetromino();
	}
	
	/**
	 * Check for completed rows and delete those that have been completed
	 * increase score
	 */
	private void completeRows()
	{
		
		// TODO: update display
	}
	
}

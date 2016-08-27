package tetris;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class for holding and managing the grid portion of the game
 * 
 * This class handles the main data and logic of the game, including
 * collision detection, sending movement and rotation instructions, 
 * deletion of rows, score calculation,
 * tetromino creation and management,
 * and overlay of grid data for display
 * 
 * These all interact to form the internal game board
 * 
 */
public class GameSystem {
	private ArrayList<Tetromino> nextShapeQueue;
	private Tetromino activeTetromino;
	
	private GameDisplay display;
	
	private boolean blockGrid[][];
	
	private static char[] shapeChoices = {'i', 'o', 's', 't', 'z', 'j', 'l'};
	private static int HEIGHT = 30;
	private static int WIDTH = 14;
	
	/**
	 * Default constructor for game system
	 * 
	 * @param disp Reference to the GameDisplay object
	 */
	public GameSystem(GameDisplay disp){
		this.display = disp;
		
		// Game board is 30 blocks high by 14 blocks wide
		this.blockGrid = new boolean[HEIGHT][WIDTH];
		
		// Initialize Tetrominos
		this.nextShapeQueue = new ArrayList<Tetromino>();
		for (int i = 0; i < 3; i++)
		{
			this.nextShapeQueue.add(createRandomTetromino());
		}
	}
	
	/**
	 * Get grid for runner use in display
	 * @return blockGrid
	 */
	public boolean[][] getGrid(){
		return this.blockGrid;
	}
	
	//Tetromino Functionality
	/**
	 * Creates a random-shaped tetromino (of the shape options)
	 * @return random tetromino
	 */
	static Tetromino createRandomTetromino(){
		Random rand = new Random();
		
		return new Tetromino(shapeChoices[rand.nextInt(shapeChoices.length)]);
	}
	
	/**
	 * Combine tetromino and grid data for display
	 * @return combined boolean grid
	 */
	public boolean[][] overlayTetromino()
	{
		int i, j;
		
		if (this.activeTetromino != null)
		{
			// Get location of active tetromino
			boolean[][] tempGrid = this.activeTetromino.getShapeGrid().clone();
			int x_loc = this.activeTetromino.getXLocation();
			int y_loc = this.activeTetromino.getYLocation();
			
			boolean[][] overlaidGrid = new boolean[HEIGHT][WIDTH];
			for (i = 0; i < this.blockGrid.length; i++) //rows
			{
				for (j = 0; j < this.blockGrid[0].length; j++) //columns
				{
					overlaidGrid[i][j] = this.blockGrid[i][j];
				}
			}
			
			// Get positions of each of its blocks
			// Incorporate these into the current grid
			for (i = 0; i < tempGrid.length; i++) //rows
			{
				for (j = 0; j < tempGrid[0].length; j++) //columns
				{
					if (tempGrid[i][j])
					{
						int new_y = i + y_loc;
						int new_x = j + x_loc;
						if ( new_y >= 0 && new_y < HEIGHT && new_x >= 0 && new_x < WIDTH)
						{
							overlaidGrid[i + y_loc][j + x_loc] = true;
						}
					}
				}
			}
		
		return overlaidGrid;
		}
		else
		{
			return this.blockGrid;
		}
	}
	
	/**
	 * Overlay next tetromino grid over 8x6 for display of shape preview
	 * @return overlaidGrid
	 */
	public boolean[][] overlayPreview()
	{
		int i, j;
		boolean[][] overlaidGrid = new boolean[8][6];
		boolean[][] previewGrid = this.nextShapeQueue.get(0).getShapeGrid();
		
		for (i = 0; i < overlaidGrid.length; i++) //rows
		{
			for (j = 0; j < overlaidGrid[0].length; j++) //columns
			{
				overlaidGrid[i][j] = false;
			}
		}
		
		for (i = 0; i < previewGrid.length; i++) //rows
		{
			for (j = 0; j < previewGrid[0].length; j++) //columns
			{
				overlaidGrid[i+2][j+2] = previewGrid[i][j];
			}
		}
		
		return overlaidGrid;
	}
	
	/**
	 * Finds active tetromino and moves it
	 * 
	 * @param x_direction -1 or 1
	 * @param y_direction -1 or 1
	 * @param rotation
	 * 
	 * x = -1 means move left, x = 1 means move right
	 * y = -1 means move up, y = 1 means move down
	 * rotation = 1 means rotate right/clockwise 90 degrees
	 * rotation = -1 means rotate left/counter-clockwise 90 degrees
	 * 
	 * Detect collision and prevent movement into walls/other shapes/etc
	 * 
	 */
	public void moveActiveTetromino(int x_direction, int y_direction, int rotation){
		boolean[][] tempGrid;
		int x_loc;
		int y_loc;
		int i;
		int j;
		
		if(GameRunner.PAUSED)
		{
			return;
		}
		
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
					if (tempGrid[i][j] && (j+x_loc <= 0))
					{
						// Undo rotation
						this.activeTetromino.rotate(rotation*-1);;
						return;
					}
					
					// Out of bounds to the right
					if (tempGrid[i][j] && (j+x_loc >= WIDTH))
					{
						// Undo rotation
						this.activeTetromino.rotate(rotation*-1);;
						return;
					}
					
					// Out of bounds to the bottom (someone tried rotating as it released
					if (tempGrid[i][j] && (i+y_loc >= HEIGHT))
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
							if ((j+x_loc >= WIDTH-1))
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
		this.display.updateGridDisplay(overlayTetromino());
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
	protected void setActiveTetromino(){
		// Pop tetromino from queue
		// Hold it in variable
		this.activeTetromino = this.nextShapeQueue.remove(0);
		
		// Add a new tetromino
		this.nextShapeQueue.add(createRandomTetromino());
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
	protected void releaseTetromino(){
		// Generate initial tetromino position		
		boolean[][] tempGrid = this.activeTetromino.getShapeGrid();
		int x_pos = 0; //leftmost x position to "center" tetromino in grid
		// 0 1 2 3 4 5 6 7 8 9 10 11 12 13
		x_pos = 5;
		
		
		// If tetromino cannot move up, game over
		if (this.blockGrid[HEIGHT-1][x_pos] || this.blockGrid[HEIGHT-2][x_pos])
		{
			GameRunner.loseGame();
			return;
		}
		
		this.activeTetromino.setLocation(x_pos, HEIGHT-2);
		
		// Update preview with next tetromino to come
		this.display.updatePreview(overlayPreview());
		
	}
	
	/** 
	 * Land and lock shape to game grid
	 * Increase score and update display
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
					int new_y = i + y_loc;
					int new_x = j + x_loc;
					if ( new_y >= 0 && new_y < HEIGHT && new_x >= 0 && new_x < WIDTH)
					{
						this.blockGrid[i + y_loc][j + x_loc] = true;
					}
				}
			}
		}
		
		GameRunner.scoreUp(GameRunner.SHAPE_LAND_SCORE);
		
		completeRows();
		
		setActiveTetromino();		
		releaseTetromino();
	}
	
	/**
	 * Check for completed rows and delete those that have been completed
	 * Increase score and update display
	 */
	private void completeRows()
	{
		boolean rowComplete;
		for (int i = 0; i < HEIGHT; i++) //rows
		{
			rowComplete = true;
			for (int j = 0; j < WIDTH; j++) //columns
			{
				if (!this.blockGrid[i][j])
				{
					rowComplete = false;
				}
			}
			if (rowComplete)
			{
				// Remove all blocks in row
				for (int k = 0; k < WIDTH; k++)
				{
					this.blockGrid[i][k] = false;
				}
				// Shift all other blocks up
				for (int m = i; m < HEIGHT-1; m++) //avoid running over edge
				{
					for (int n = 0; n < WIDTH; n++)
					{
						this.blockGrid[m][n] = this.blockGrid[m+1][n];
					}
				}
				
				GameRunner.scoreUp(GameRunner.ROW_COMPLETE_SCORE);
				
				i--; //check that row one more time
			}
		}
		this.display.updateGridDisplay(overlayTetromino());
	}
	
}

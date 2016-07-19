package tetris;

import java.util.ArrayList;

/**
 * Class for holding and managing individual game components
 * 
 * This class handles the main data & logic of the game, including
 * collision detection, movement & rotation, deletion of rows, score calculation,
 * tetromino creation and management
 * 
 * These all interact to form the "state" of the game
 * 
 */
public class GameSystem {
	private int score;
	private ArrayList<Tetromino> nextShapeQueue;
	private Tetromino activeTetromino;
	
	public boolean blockGrid[][];
	
	/**
	 * Default constructor for game system
	 * 
	 * @return game system object
	 * 
	 */
	public GameSystem(){
		// Set score to zero
		
		// Game board is 22 blocks high by 10 blocks wide
		
		// Initialize Tetrominos
		
	}
	
	/**
	 * Get grid for runner use in display
	 * @return blockGrid
	 */
	public static boolean[][] getGrid(){
		return null;
	}
	
	//Tetromino Functionality
	/**
	 * Finds active tetromino and moves it
	 * 
	 * @param x_direction
	 * @param y_direction
	 * @param rotation
	 */
	public static void moveActiveTetromino(int x_direction, int y_direction, int rotation){
		//TODO: possibly add a return value to indicate losing game
	}
	
	/**
	 * Clean up active tetromino
	 */
	static void removeActiveTetromino(){
		
	}
	
	/**
	 * Grab new active tetromino
	 */
	static void setActiveTetromino(){
		//pop tetromino from queue
		
		//hold it in variable
	}
	
	/**
	 * Get active tetromino
	 * @return active tetromino
	 */
	public static Tetromino getActiveTetromino(){
		return null;
	}
	
	/**
	 * Release tetromino so it can begin moving
	 */
	static void releaseTetromino(){
		//begin moving active tetromino through board
	}
	
	/** 
	 * land and lock shape to game grid
	 */
	static void landShape(){
		
	}
	
	/**
	 * Check for completed rows and delete those that have been completed
	 */
	static void completeRows(){
		
	}
	
}

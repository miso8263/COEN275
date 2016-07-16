package tetris;

import java.util.ArrayList;

/**
 * Class for coordinating individual game components and running the main gameplay.
 * 
 */
public class GameSystem {
	private int score;
	private ArrayList<Tetromino> nextShapeQueue;
	private Tetromino activeTetromino;
	
	public boolean displayGrid[][];
	
	/**
	 * Default constructor for game system
	 * 
	 * @return game system object
	 * 
	 */
	public GameSystem(){
		// Initialize display
		
		// Initialize Tetrominos
		
		// Initialize Timer
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

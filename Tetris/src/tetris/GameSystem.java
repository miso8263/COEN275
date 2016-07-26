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
	
	private static char[] shapeChoices = {'i', 'o', 's', 'z', 'j', 'l'};
	private static Random rand = new Random();
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
		this.blockGrid = new boolean[22][10];
		
		// Initialize Tetrominos
		this.nextShapeQueue = new ArrayList<Tetromino>();
		for(int i = 0; i < 3; i++){
			nextShapeQueue.add(new Tetromino(shapeChoices[rand.nextInt(5)]));
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
		return new Tetromino();
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
	 * rotation = ?? means rotate ?? 90 degrees
	 * 
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
	 * increase score
	 */
	static void landShape(){
		
	}
	
	/**
	 * Check for completed rows and delete those that have been completed
	 * increase score
	 */
	static void completeRows(){
		
	}
	
}

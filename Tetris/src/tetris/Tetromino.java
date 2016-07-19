package tetris;

/**
 * Template for tetromino characteristics and creation.
 * 
 * Contains the components necessary for a tetromino to exist within the game
 * Minimal logic to implement selection of tetromino shape upon creation
 *
 */
public class Tetromino {
	
	private boolean[][] shape; //this may be implemented in a different way
						// but it must match the return type of getShape()
	
	private int x_location;
	private int y_location;
	
	/**
	 * Constructor for tetromino
	 * Grabs shape character and initializes shape array based on selection
	 * @param shape
	 * char shape may be 'i', 'o', 's', 'z', j', or 'l'
	 */
	public Tetromino(char shape){
		//if shape == 'l' shape ==  oooo
		
		//else if shape == 'o' shape == oo
		//                              oo
		
		//etc
	}
	
	/**
	 * Retriever for shape data
	 * Used by system and display
	 * @return shape
	 */
	public boolean[][] getShape(){
		return this.shape;
	}
	
	/**
	 * Getter for x location
	 * X position is specified as:
	 * TODO: decide how
	 * @return this.x_location
	 */
	public int getXLocation(){
		return -1;
	}
	
	/**
	 * Getter for y location
	 * Y position is specified as:
	 * TODO: decide how
	 * @return this.y_location
	 */
	public int getYLocation(){
		return -1;
	}
	/**
	 * Set location of tetromino object
	 * In relation to:
	 * TODO: should match setters and whatever corner of the grid
	 * @param x
	 * @param y
	 */
	public void setLocation(int x, int y){
		
	}
	
	/**
	 * Rotate tetromino clockwise or counter clockwise
	 * Clockwise: rotation == ??
	 * Counter-clockwise: rotation == ??
	 * @param rotation
	 */
	public void rotate(int rotation){
		// TODO: add collision detection and error handling
	}
}




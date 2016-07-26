package tetris;


/**
 * Template for tetromino characteristics and creation.
 * 
 * Contains the components necessary for a tetromino to exist within the game
 * Minimal logic to implement selection of tetromino shape upon creation
 *
 */
public class Tetromino {
	
	private boolean[][] tetrominoGrid; 
	private char tetrominoShape; //adding this in in case we need it later since implementation is very easy
	private boolean T = true;
	private boolean F = false;
	
	private int x_location;
	private int y_location;
	
	/**
	 * Default constructor for tetromino
	 * Creates a square tetromino ('o')
	 */

	public Tetromino(){
	
		this.tetrominoGrid = new boolean [][] {{T, T},
											   {T, T}};
		this.tetrominoShape = 'o';
	}
	
	/**
	 * Constructor for tetromino
	 * Grabs shape character and initializes shape array based on selection
	 * @param shape
	 * char shape may be 'i', 'o', 't', 's', 'z', j', or 'l'
	 */
	public Tetromino(char shape){
		
		boolean [][] newTetrominoGrid = null;
		
		this.x_location = -111;
		this.y_location = -111;
		
		switch(shape) {
			case 'i':
				newTetrominoGrid = new boolean [][] {{F, F, F, F},
													 {T, T, T, T},
													 {F, F, F, F},												
													 {F, F, F, F}};
				break;
				
			case 'o':	
				newTetrominoGrid = new boolean [][] {{T, T},
													 {T, T}};
				break;
			
			case 't':
				newTetrominoGrid = new boolean [][] {{F, T, F},
													 {T, T, T},
													 {F, F, F}};		
				break;
				
			case 's':
				newTetrominoGrid = new boolean [][] {{F, T, T},
													 {T, T, F},
													 {F, F, F}};		
				break;
					
			case 'z':	
				newTetrominoGrid = new boolean [][] {{T, T, F},
											  		 {F, T, T},
											  		 {F, F, F}};			
				break;
				
			case 'j':
				newTetrominoGrid = new boolean [][] {{T, F, F},
											  		 {T, T, T},
											  		 {F, F, F}};	
				break;
				
	
			case 'l':
				newTetrominoGrid = new boolean [][] {{F, F, T},
											  		 {T, T, T},
											  		 {F, F, F}};
				break; 
				
			default:
				System.out.println("Invalid shape provided to constructor.");
				break;
		}
		
		this.tetrominoGrid = newTetrominoGrid;
		this.tetrominoShape = shape;
	}
	
	/**
	 * Retriever for shape data
	 * Used by system and display
	 * @return shape
	 */
	public boolean[][] getShapeGrid(){
		return this.tetrominoGrid;
	}
	
	/**
	 * Getter for x location
	 * X position is specified as:
	 * TODO: decide how
	 * @return this.x_location
	 */
	public int getXLocation(){
		return this.x_location;
	}
	
	/**
	 * Getter for y location
	 * Y position is specified as:
	 * TODO: decide how	
	 * @return this.y_location
	 */
	public int getYLocation(){
		return this.y_location; /*T: I believe this should just return the location that has already been stored
										it is setting that location that we need to figure out how to do */
	}
	/**
	 * Set location of tetromino object
	 * In relation to: the game board grid?
	 * TODO: should match setters and whatever corner of the grid, current working: say upper left corner
	 * @param x
	 * @param y
	 */
	public void setLocation(int x, int y){
		
		this.x_location = x;
		this.y_location = y;
	}
	
	/**
	 * Rotate tetromino 90 degrees clockwise or counter clockwise.
	 * Bigger rotations are a result of the rotate function being called
	 * repeatedly. 
	 * Clockwise: rotation == 1
	 * Counter-clockwise: rotation == -1
	 * @param rotation
	 */
	
	public void rotate(int rotation){
		// TODO: add collision detection and error handling
		/* I thought collision detection would be handled by the system since the tetromino doesn't know about
		 * anything outside itself? Perhaps system can check for collision every time the tetromino moves. */
		switch (rotation) {
			case 1: 
				//rotate right
				swapRows(this.tetrominoGrid);
				transpose(this.tetrominoGrid);
				break;
			case -1:
				//rotate left
				transpose(this.tetrominoGrid);
				swapRows(this.tetrominoGrid);
				break;
			default:
				//no rotation
				//do nothing
				break;
		}
	}
	//should be private static void
	private static void swapRows(boolean[][] tetrominoGrid){
		for (int i = 0, j = tetrominoGrid[i].length-1; i<j; i++, j--){
			boolean [] holder =  tetrominoGrid[i];
			tetrominoGrid[i] = tetrominoGrid[j];
			tetrominoGrid[j] = holder;
		}
	}
	//should be private static void
	private static void transpose(boolean[][] tetrominoGrid){ 
		
		boolean [][] holder = new boolean [tetrominoGrid.length][];
		for( int i = 0; i < tetrominoGrid.length; i++){
			holder [i] = tetrominoGrid[i].clone();
		}
		
		for(int i = 0; i < tetrominoGrid.length; i++ ) {
			for( int j = 0; j < tetrominoGrid.length; j++ ) {
				tetrominoGrid[i][j] = holder[j][i];
			}
		}
			
	}

}






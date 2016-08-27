package tetris;


/**
 * Template for tetromino characteristics and creation.
 * 
 * Contains the components necessary for a tetromino to exist within the game
 * Minimal logic to implement selection of tetromino shape upon creation
 * As well as rotation handling
 *
 */
public class Tetromino {
	
	private boolean[][] tetrominoGrid; 
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
	}
	
	/**
	 * Constructor for tetromino
	 * Grabs shape character and initializes shape array based on selection
	 * @param shape Shape choice may be 'i', 'o', 't', 's', 'z', j', or 'l'
	 */
	public Tetromino(char shape){
		
		boolean [][] newTetrominoGrid = null;
		
		// Initial values that corner of tetromino grid could NEVER be at while in play
		// Used by System to ensure non-released tetrominos are not moved
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
	}
	
	/**
	 * Retriever for shape data
	 * Used by system for collision handling and display
	 * @return tetrominoGrid
	 */
	public boolean[][] getShapeGrid(){
		return this.tetrominoGrid;
	}
	
	/**
	 * Getter for x location
	 * X position is specified as:
	 * 0 == far left
	 * @return this.x_location
	 */
	public int getXLocation(){
		return this.x_location;
	}
	
	/**
	 * Getter for y location
	 * Y position is specified as:
	 * 0 == top
	 * @return this.y_location
	 */
	public int getYLocation(){
		return this.y_location; 
	}
	
	/**
	 * Set location of tetromino object
	 * In relation to: the game board grid
	 * This position is the upper left corner of the tetromino grid
	 * @param x X position of upper left corner of tetromino grid
	 * @param y Y position of upper left corner of tetromino grid
	 */
	public void setLocation(int x, int y){
		
		this.x_location = x;
		this.y_location = y;
	}
	
	/**
	 * Rotate tetromino 90 degrees clockwise or counter clockwise.
	 * Bigger rotations are a result of the rotate function being called
	 * repeatedly. 
	 * 
	 * @param rotation 1 for clockwise, -1 for counter-clockwise
	 */
	public void rotate(int rotation){
		switch (rotation) {
			case 1: 
				//rotate right/clockwise
				swapRows();
				transpose();
				break;
			case -1:
				//rotate left/counter-clockwise
				transpose();
				swapRows();
				break;
			default:
				//no rotation
				//do nothing
				break;
		}
	}
	
	/**
	 * swapRows
	 * helper function for row-swapping portion of rotation
	 */
	private void swapRows(){
		for (int i = 0, j = this.tetrominoGrid[i].length-1; i<j; i++, j--){
			boolean [] holder =  this.tetrominoGrid[i];
			this.tetrominoGrid[i] = this.tetrominoGrid[j];
			this.tetrominoGrid[j] = holder;
		}
	}
	
	/**
	 * transpose
	 * helper function for transpose portion of rotation
	 */
	private void transpose(){ 
		
		boolean [][] holder = new boolean [this.tetrominoGrid.length][];
		for( int i = 0; i < this.tetrominoGrid.length; i++){
			holder [i] = this.tetrominoGrid[i].clone();
		}
		
		for(int i = 0; i < this.tetrominoGrid.length; i++ ) {
			for( int j = 0; j < this.tetrominoGrid.length; j++ ) {
				this.tetrominoGrid[i][j] = holder[j][i];
			}
		}
			
	}
}






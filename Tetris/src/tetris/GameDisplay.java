package tetris;

/**
 * Class for displaying game elements to the screen.  
 * 
 * This will be driven by the GameRunner with data retrieved from GameSystem
 * 
 * This is going to have some fields but we haven't worked that out yet
 */
public class GameDisplay {
	//TODO: add fields, such as String flavorText, actual display, etc.
	
	/**
	 * Default constructor
	 * initialize default display
	 */
	public GameDisplay(){
		
		// Game board is 22 blocks high by 10 blocks wide
		
		// Game board has a border
		
	}
	
	/**
	 * Update display
	 * @param gridData
	 */
	public static void updateGridDisplay(boolean[][] gridData){
		
	}
	
	/**
	 * Update flavor text (pause, quit, etc)
	 * @param text
	 */
	public static void updateFlavorText(String text){
		
	}
	
	/**
	 * Update preview of next shape to come
	 * @param next tetromino
	 */
	public static void updatePreview(Tetromino tetromino){
		
	}

}

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
	 * gridData is a boolean grid with the same dimensions as the display
	 * 0 means no block present
	 * 1 means block present
	 */
	public static void updateGridDisplay(boolean[][] gridData){
		
	}
	
	/**
	 * Update game message (pause, quit, etc)
	 * @param text
	 */
	public static void updateGameMessage(String text){
		
	}
	
	/**
	 * Update preview of next shape to come
	 * @param next tetromino
	 */
	public static void updatePreview(Tetromino tetromino){
		
	}
	
	/**
	 * Display score
	 * @param score
	 */
	public static void updateScoreDisplay(int score){
		
	}

}

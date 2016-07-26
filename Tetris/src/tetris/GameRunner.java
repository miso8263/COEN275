package tetris;

import java.awt.event.KeyListener;

/**
 * Class for interacting with the user and executing gameplay instructions.
 * 
 * Sends commands to the GameSystem for management of gameplay,
 * including sending signals received from user and "drop" timer
 * 
 * Retrieves details from GameSystem to transmit to the display
 * Also sends flavor text depending on game state to GameDisplay
 * 
 * Runs major start, pause, and quit functionality
 * 
 */
public class GameRunner{
	private static int level;
	private static boolean paused;
	/**
	 * Default constructor for the Runner class
	 * 
	 * @return instance of Runner class
	 */
	GameRunner(){
		
	}
	
	/**
	 * Function to begin game 
	 */
	static void startGame(){
		// Create game system object
		GameSystem tetrisSystem = new GameSystem();
		
		// Set level to zero
		level = 0;
		
		//paused is false
		paused = false;
		
		// Initialize display
		GameDisplay tetrisDisplay = new GameDisplay();
		
		// Initialize Timer
		Timekeeper tetrisTimer = new Timekeeper();
		
		// Initialize Input Listener
	}
	
	/**
	 * This will become the input listener, which passes user input to system
	 * 
	 * @param user input
	 */
	static void inputListener(){
		// Listener: Press A
			// Behavior: Rotate counterclockwise
		// Listener: Press D
			// Behavior: Rotate clockwise
		
		// Listener: Press Left arrow
			// Behavior: Shift Tetromino left 
		
		// Listener: Press Right arrow
			// Behavior: Shift Tetromino right
		
		// Listener: Press Down arrow
			// Behavior: "Drop" Tetromino into place
		
		//Listner: Press Spacebar
			// Behavior: Pause Game
		
		// Listener: Press Esc
			// Behavior: Pause Game
			// Print "Quit Game"
			// If they press Enter, Quit Game
	
	}
	
	static void levelUp(){
		// Print level up message
		// Speed of drop is increased
		// Score threshold is updated

	}
	
	/**
	 * Stop taking input until game is unpaused
	 */
	static void pauseGame(){
		//If game already paused,
			//unpause
			//Pause message is removed
			//Game clock resumes, game loop resumes
		//Else
			//game clock stops, game loop suspends
			//Active tetromino ceases all movement, including downward 
			//Pause message is displayed

	}
	
	/**
	 * Lose game
	 */
	static void loseGame(){
		// Loser message printed
		// call endGame
	}
	
	/**
	 * End game
	 */
	static void endGame(){
		//Game ends
		//Final scores is printed
		//Provide option to play again, if yes Case A executes
	}
	
	/**
	 * Main runner for the game
	 * @param args
	 */
	public static void main(String[] args){
		startGame();
		
		// Begin gameplay
		System.out.println("Game not implemented yet.  Try again later.\n");
				
		// While game is not over
		
			// Release Tetromino
			// If there is no room, lose game
			// Update preview of next shape
			// While tetromino has not landed
				// Wait
			// Land tetromino
			// Complete rows
			// Update score
			// If score is high enough to level up
			// Level up
	}

}

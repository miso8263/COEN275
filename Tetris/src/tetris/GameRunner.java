package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

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
	private static GameDisplay tetrisDisplay;
	private static GameSystem tetrisSystem;
	private static Timer gameTimer;
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
		
		// Initialize display
		tetrisDisplay = new GameDisplay();
				
		// Create game system object
		tetrisSystem = new GameSystem(tetrisDisplay);
		
		// Set level to zero
		level = 0;
		
		//paused is false
		paused = false;
		
		
		
		// Initialize Timer
		Timekeeper tetrisTimer = new Timekeeper(1000, tetrisSystem);
		gameTimer = new Timer();
		
		
		// Initialize Input Listener
		initializeListener();
		
		tetrisSystem.setActiveTetromino();
		
		tetrisSystem.releaseTetromino();
		
		System.out.println("DAT SPEED THO" +tetrisTimer.getSpeed());
		
		gameTimer.scheduleAtFixedRate(tetrisTimer, 0, tetrisTimer.getSpeed());
	}
	
	/**
	 * This will become the input listener, which passes user input to system
	 * 
	 */
	static void initializeListener(){
			
		Action aAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				// Listener: Press A
				// Behavior: Rotate counterclockwise
				System.out.println("A pressed");
				tetrisSystem.moveActiveTetromino(0, 0, -1);
			}
		};

		Action dAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				// Listener: Press D
				// Behavior: Rotate clockwise
				System.out.println("D pressed");
				tetrisSystem.moveActiveTetromino(0, 0, 1);
			}
		};
		
		Action leftAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				// Listener: Press Left arrow
				// Behavior: Shift Tetromino left 
				System.out.println("left pressed");
				tetrisSystem.moveActiveTetromino(-1, 0, 0);
			}
		};

		Action rightAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				// Listener: Press Right arrow
				// Behavior: Shift Tetromino right
				System.out.println("right pressed");
				tetrisSystem.moveActiveTetromino(1, 0, 0);
			}
		};
		
		Action upAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				// Listener: Press Up arrow
				// Behavior: "Lift" Tetromino into place
				tetrisSystem.moveActiveTetromino(0, -1, 0);
			}
		};

		//Listener: Press Spacebar
		// Behavior: Pause Game
		
		// Listener: Press Esc
		// Behavior: Pause Game
		// Print "Quit Game"
		// If they press Enter, Quit Game
		
		
		tetrisDisplay.getInputMap().put(KeyStroke.getKeyStroke("a"), aAction);
		tetrisDisplay.getInputMap().put(KeyStroke.getKeyStroke("d"), dAction);
		tetrisDisplay.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), leftAction);
		tetrisDisplay.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), rightAction);
		tetrisDisplay.getInputMap().put(KeyStroke.getKeyStroke("UP"), upAction);	
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
		tetrisSystem.setActiveTetromino();
		
		tetrisSystem.releaseTetromino();
				
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

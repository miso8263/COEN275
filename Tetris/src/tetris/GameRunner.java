package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

/**
 * Class for holding and managing individual game components.
 * 
 * Sends commands to the GameSystem for management of gameplay,
 * including sending signals received from user and "drop" timer
 * 
 * Retrieves and sends commands between system and display
 * Hands off references to each class for direct use
 * 
 * Runs major start, pause, and quit functionality
 * As well as level and score
 * 
 * This all interacts to form the "state" of the game
 * 
 */
public class GameRunner{
	private static int level;
	private static int score;
	private static GameDisplay tetrisDisplay;
	private static GameSystem tetrisSystem;
	private static Timekeeper tetrisTimer;
	private static Timer gameTimer;
	
	static int SCORE_THRESHOLD = 500;
	static int SHAPE_LAND_SCORE = 50;
	static int ROW_COMPLETE_SCORE = 75;
	static boolean PAUSED = true;
	
	// Actions to attach as keybindings for display
	
	static Action aAction = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
			// Listener: Press A
			// Behavior: Rotate counterclockwise
			tetrisSystem.moveActiveTetromino(0, 0, -1);
		}
	};

	static Action dAction = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
			// Listener: Press D
			// Behavior: Rotate clockwise
			tetrisSystem.moveActiveTetromino(0, 0, 1);
		}
	};
	
	static Action leftAction = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
			// Listener: Press Left arrow
			// Behavior: Shift Tetromino left 
			tetrisSystem.moveActiveTetromino(-1, 0, 0);
		}
	};

	static Action rightAction = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
			// Listener: Press Right arrow
			// Behavior: Shift Tetromino right
			tetrisSystem.moveActiveTetromino(1, 0, 0);
		}
	};
	
	static Action upAction = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
			// Listener: Press Up arrow
			// Behavior: "Lift" Tetromino into place
			tetrisSystem.moveActiveTetromino(0, -1, 0);
		}
	};
	
	/**
	 * Function to begin game 
	 * Initialize game components
	 * Give them the information they need to kick off game functionality
	 */
	static void startGame(){
		
		// Set level to one and score to zero
		level = 1;
		score = 0;
		
		// Initialize display
		tetrisDisplay = new GameDisplay();
				
		// Create game system object
		tetrisSystem = new GameSystem(tetrisDisplay);
		
		// Begin display with its initial blank grid
		tetrisDisplay.updateGridDisplay(tetrisSystem.getGrid());
		
		
		
		//paused is true until we receive the start signal from the user
		PAUSED = true;
		
		// Initialize Timer
		tetrisTimer = new Timekeeper(1000, tetrisSystem);
		gameTimer = new Timer();
		
		
		// Initialize Input Listener
		initializeListener();
		
		tetrisSystem.setActiveTetromino();
		
		tetrisSystem.releaseTetromino();
		
		gameTimer.scheduleAtFixedRate(tetrisTimer, 0, tetrisTimer.getSpeed());
	}
	
	/**
	 * Attach keybindings and actions to a panel inside the game window
	 * 
	 */
	static void initializeListener(){
		tetrisDisplay.getPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "aAction");
		tetrisDisplay.getPanel().getActionMap().put("aAction", aAction);
		tetrisDisplay.getPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "dAction");
		tetrisDisplay.getPanel().getActionMap().put("dAction", dAction);
		tetrisDisplay.getPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "leftAction");
		tetrisDisplay.getPanel().getActionMap().put("leftAction", leftAction);
		tetrisDisplay.getPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "rightAction");
		tetrisDisplay.getPanel().getActionMap().put("rightAction", rightAction);
		tetrisDisplay.getPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "upAction");
		tetrisDisplay.getPanel().getActionMap().put("upAction", upAction);
		
	}
	
	/**
	 * Increase level and update display
	 */
	static void levelUp(){
		
		level += 1;
		
		// Print level up message
		tetrisDisplay.updateLevelDisplay(level);
		
		// Score threshold is updated
		SCORE_THRESHOLD += 500;
		
		// Calculate new speed
		// Speed of drop is increased
		int newSpeed = (int)Math.round(tetrisTimer.getSpeed()*.9);		
		
		// Purge and restart timer
		gameTimer.cancel();
		gameTimer.purge();
		
		gameTimer = new Timer();
		tetrisTimer = new Timekeeper(newSpeed, tetrisSystem);

		gameTimer.scheduleAtFixedRate(tetrisTimer, 0, tetrisTimer.getSpeed());
		
	}
	
	/**
	 * Increase score by a defined number
	 * Update display and check for level up
	 * @param scoreIncrease
	 */
	static void scoreUp(int scoreIncrease)
	{
		score += scoreIncrease;
		tetrisDisplay.updateScoreDisplay(score);
		
		if (score > SCORE_THRESHOLD)
		{
			levelUp();
		}
	}
	
	/**
	 * Stop taking input until game is unpaused
	 */
	static void pauseGame(boolean _paused){
		PAUSED = _paused;
	}
	
	/**
	 * Lose game
	 */
	static void loseGame(){
		// Loser message printed
		// call endGame
		tetrisDisplay.endGame();
	}
	
	/**
	 * End game
	 */
	static void endGame(){
		// Final scores is printed
		// Provide option to play again, if yes Case A executes
		// The above are done in Display
		
		//Game ends
		System.exit(0);
	}
	
	/**
	 * Restart game
	 */
	static void restartGame(){
		// Clear old game contents
		gameTimer.cancel();
		gameTimer.purge();

		tetrisDisplay = null;
		tetrisSystem = null;
		tetrisTimer = null;
		gameTimer = null;
		
		// Start game
		startGame();
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
	}

}

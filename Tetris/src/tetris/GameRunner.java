package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
	
	private static AudioInputStream mainTheme;
	private static AudioInputStream imperialMarch;
	private static AudioInputStream vaderSound;
	private static Clip themeClip;
	private static Clip imperialClip;
	private static Clip vaderClip;
	
	static boolean gameOver;
	static Timer killVader;
	
	private static double SPEED_MODIFIER = .8; // Deliberately fast for demo purposes; for real play do .9
	
	static int scoreThreshold = 500;
	static boolean paused = true;
	static boolean reset = false;
	static int SHAPE_LAND_SCORE = 50;
	static int ROW_COMPLETE_SCORE = 75;
	
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
	
	static Action spaceAction = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
			// Pause Game
			if (!gameOver)
			{
				tetrisDisplay.pause();
			}
		}
	};
	
	static Action escAction = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
			// End game
			if (!gameOver)
			{
				tetrisDisplay.updateSassyVader("Quit");
				tetrisDisplay.endGame();
			}
		}
	};
	
	/**
	 * Function to begin game 
	 * Initialize game components
	 * Give them the information they need to kick off game functionality
	 */
	static void startGame(){
		// game is starting
		gameOver = false;
		
		//paused is true until we receive the start signal from the user
		paused = true;
		reset = true;
		
		// Set level to one and score to zero
		level = 1;
		score = 0;
		scoreThreshold = 500;
		
		// Initialize display
		tetrisDisplay = new GameDisplay();
				
		// Create game system object
		tetrisSystem = new GameSystem(tetrisDisplay);
		
		// Begin display with its initial blank grid
		tetrisDisplay.updateGridDisplay(tetrisSystem.getGrid());		
		
		// Initialize Timer
		tetrisTimer = new Timekeeper(1000, tetrisSystem);
		gameTimer = new Timer();
		
		
		// Initialize Input Listener
		initializeListener();
		
		// Begin ability for tetromino to move
		tetrisSystem.setActiveTetromino();
		
		tetrisSystem.releaseTetromino();
		
		gameTimer.scheduleAtFixedRate(tetrisTimer, 0, tetrisTimer.getSpeed());
		
		// Start music
		try {
			startTheme();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
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
		
		// Pause
		tetrisDisplay.getPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "spaceAction");
		tetrisDisplay.getPanel().getActionMap().put("spaceAction", spaceAction);
		
		// Quit
		tetrisDisplay.getPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escAction");
		tetrisDisplay.getPanel().getActionMap().put("escAction", escAction);
		
	}
	
	/**
	 * Increase level and update display
	 */
	static void levelUp(){
		level += 1;
		reset = false;
		
		// Print level up message
		tetrisDisplay.updateLevelDisplay(level);
		tetrisDisplay.updateSassyVader("Level Up");
		
		killVader = new Timer();
		killVader.schedule(new TimerTask(){
			@Override
			public void run(){
				if (!gameOver && !reset)
				{
					tetrisDisplay.updateSassyVader("");;
				}
			}
		}, 3000);
		
		// Score threshold is updated
		scoreThreshold += 500;
		
		// Calculate new speed
		// Speed of drop is increased
		int newSpeed = (int)Math.round(tetrisTimer.getSpeed()*SPEED_MODIFIER);		
		
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
	 * @param scoreIncrease Number to increase score by
	 */
	static void scoreUp(int scoreIncrease)
	{
		score += scoreIncrease;
		tetrisDisplay.updateScoreDisplay(score);
		
		if (score > scoreThreshold)
		{
			levelUp();
		}
	}
	
	/**
	 * Stop taking input until game is unpaused
	 * @param _paused New T/F value for paused
	 */
	static void pauseGame(boolean _paused){
		if (!gameOver)
		{
			paused = _paused;
		}
		else
		{
			paused = true;
		}
	}
	
	/**
	 * Lose game
	 */
	static void loseGame(){
		// Loser message printed
		// call endGame
		tetrisDisplay.updateSassyVader("Game Over");
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
		
		stopMusic();
		
		// Start game
		startGame();
	}
	
	// Music Handling
	
	static void startTheme() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		// Sound From http://www.moviewavs.com/Movies/Star_Wars.html
		mainTheme = AudioSystem.getAudioInputStream(new File("starwars.wav"));
		themeClip = AudioSystem.getClip();
		themeClip.open( mainTheme );
		themeClip.start();
	}
	
	static void startMarch() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		// Sound From http://www.moviewavs.com/Movies/Star_Wars.html
		imperialMarch = AudioSystem.getAudioInputStream(new File("imperial.wav"));
	    imperialClip = AudioSystem.getClip();
	    imperialClip.open( imperialMarch );
	    imperialClip.start();
	}
	
	static void startVader() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		// Sound From http://www.thesoundarchive.com/starward/swvader02.wav
		vaderSound = AudioSystem.getAudioInputStream(new File("VaderBreathing.wav"));
		vaderClip = AudioSystem.getClip();
		vaderClip.open( vaderSound );
		vaderClip.start();
	}
	
	static void stopMusic()
	{
		if (themeClip != null)
		{
			themeClip.stop();
		}
		if (imperialClip != null)
		{
			imperialClip.stop();
		}
	}
	
	/**
	 * Main runner for the game
	 * @param args Arguments to main (unused)
	 */
	public static void main(String[] args){
		startGame();
	}

}

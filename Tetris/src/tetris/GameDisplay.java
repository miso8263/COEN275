package tetris;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/**
 * Class for displaying game elements to the screen.  
 * 
 * This will be driven by the GameRunner with data retrieved from GameSystem
 * 
 * Contains all components and resources necessary to display information
 * And grab input from user, which is transmitted to the runner/system
 */
public class GameDisplay extends JComponent{
	
	static JFrame frame;
	
	static JPanel tetrisPanel;
	static JPanel controlPanel;
	static JPanel sassyvaderPanel;
	static JPanel screensContainer;
	static CardLayout screens;
	static JLayeredPane layeredContainer;
	static JLabel sassyvader;
	static JPanel previewPanel;
	static JLabel[][] playgrid;
	static JLabel[][] previewgrid;
	
	static JPanel gameoverPanel;
	static JPanel pausePanel; 
	
	static ImageIcon vadericon;
	
	JLabel scoreDisplay;
	JLabel levelDisplay;
	
	static boolean pausestate;
	static boolean lockout;
	
	private java.awt.Image image;
	
	//Image source: created by Tatiana Petkova 
	static ImageIcon minoIcon = new ImageIcon(new ImageIcon("Mino.png").getImage().getScaledInstance(19, 19, Image.SCALE_DEFAULT));
	
	public void ImagePanel(java.awt.Image image){
		this.image = image;
	}
	
	/**
	 * Default constructor
	 * initialize default display
	 */
	public GameDisplay(){
		
		// Game board is 28 blocks high by 14 blocks wide
		// Game board has a border
		initialize();
	}
	
	
	/**
	 * Update display
	 * @param gridData
	 * gridData is a boolean grid with the same dimensions as the display
	 * 0 means no block present
	 * 1 means block present
	 */
	public static void updateGridDisplay(boolean[][] gridData){

		
		boolean blankGrid = true;
		
		for (int i = 0; i < gridData.length-2; i++) {	
	    {
	    	for (int j = 0; j < gridData[0].length; j++)
	            {
	                if (gridData[i][j])
	                {
	                	playgrid[i][j].setIcon(minoIcon);
	                	blankGrid = false;
	                }  
	                else
	                {
	                	playgrid[i][j].setIcon(null);
	                }
	            }
	        }	    	
		}
		
		if (blankGrid)
		{
			// Nobody should ever see this; it just forces the window up on the screen for smooth transition between blank/not blank grid
			playgrid[0][0].setIcon(new ImageIcon(new ImageIcon("akbar.png").getImage().getScaledInstance(19, 19, Image.SCALE_DEFAULT)));
		}
		
		frame.setVisible(true);
	}
	
	/**
	 * Update sassy vader image (pause, quit, etc)
	 * @param event Event strings include Pause, Game Over, Four, Level Up
	 */
	public static void updateSassyVader(String event){
		
		Image vaderimage;
		boolean notVader = false;
	
		if (event == "Pause"){
				//Image source: https://media2.popsugar-assets.com/files/2015/05/11/825/n/1922398/d5db8e92_shutterstock_239338216.xxxlarge_2x.jpg
				//Caption added by Tatiana Petkova
				vaderimage = new ImageIcon("VaderPause.png").getImage().getScaledInstance(256,164, Image.SCALE_DEFAULT);
				vadericon.setImage(vaderimage);
				sassyvader.revalidate();
				sassyvader.repaint();
				sassyvader.update(sassyvader.getGraphics());
		}
		else if (event == "Quit"){
				//Image source: https://media2.popsugar-assets.com/files/2015/05/11/825/n/1922398/d5db8e92_shutterstock_239338216.xxxlarge_2x.jpg
				//Caption added by Tatiana Petkova
				vaderimage = new ImageIcon("VaderQuit.png").getImage().getScaledInstance(256,164, Image.SCALE_DEFAULT);
				vadericon.setImage(vaderimage);
				sassyvader.revalidate();
				sassyvader.repaint();
				sassyvader.update(sassyvader.getGraphics());	
		}
		else if (event == "Game Over"){
				//Image source: http://www.ramascreen.com/wp-content/uploads/2014/06/Darth-Vader-e1436305399250.jpg
				//Caption added by Tatiana Petkova
				vaderimage = new ImageIcon("VaderDeath.png").getImage().getScaledInstance(256,164, Image.SCALE_DEFAULT);
				vadericon.setImage(vaderimage);
				sassyvader.revalidate();
				sassyvader.repaint();
				sassyvader.update(sassyvader.getGraphics());	
		}
		else if(event == "Level Up"){
				//Image source: http://cdn.playbuzz.com/cdn/2bec0346-34b5-4853-9a88-0ff6bb39717f/37ad5bf3-378e-40ef-99b1-fefcad14cee8.jpg
				//Caption added by Tatiana Petkova
				vaderimage = new ImageIcon("VaderForce.png").getImage().getScaledInstance(256,164, Image.SCALE_DEFAULT);
				vadericon.setImage(vaderimage);
				sassyvader.revalidate();
				sassyvader.repaint();
				sassyvader.update(sassyvader.getGraphics());
		}
		else if (event == "Four"){
				//Image source: http://cdn.playbuzz.com/cdn/2bec0346-34b5-4853-9a88-0ff6bb39717f/37ad5bf3-378e-40ef-99b1-fefcad14cee8.jpg
				//Caption added by Tatiana Petkova
				vaderimage = new ImageIcon("VaderFour.png").getImage().getScaledInstance(256,164, Image.SCALE_DEFAULT);
				vadericon.setImage(vaderimage);
				sassyvader.revalidate();
				sassyvader.repaint();
				sassyvader.update(sassyvader.getGraphics());	
		}
		else{
			//Image source: http://vignette3.wikia.nocookie.net/starwars/images/9/96/Death_star_blueprints_sw_card_trader.png/revision/latest?cb=20160620042622
			vaderimage = new ImageIcon("DeathStarBlueprintWide.png").getImage().getScaledInstance(256,164, Image.SCALE_DEFAULT);
			vadericon.setImage(vaderimage);
			sassyvader.revalidate();
			sassyvader.repaint();
			sassyvader.update(sassyvader.getGraphics());	
			notVader = true;
		}
		
		if (!notVader)
		{
			try {
				GameRunner.startVader();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * Update preview of next shape to come
	 * @param previewData 8x6 boolean grid to display as shape preview
	 * to be consistent with the handling of the tetromino game board. 
	 */
	public static void updatePreview(boolean[][] previewData){
		
		for (int i = 0; i < previewData.length; i++) {	
		
	    	for (int j = 0; j < previewData[0].length; j++) {
	                if (previewData[i][j])
	                {
	                	previewgrid[i][j].setIcon(minoIcon);
	                }
	                else
	                {
	                	previewgrid[i][j].setIcon(null);
	                }
	        }
	    }	    	
		frame.setVisible(true);
	}
	
	/**
	 * Display score
	 * @param score Current game score to display in score window
	 */
	public void updateScoreDisplay(int score){
		this.scoreDisplay.setText(""+score);
	}
	
	/**
	 * Display level
	 * @param level Current game level to display in level window
	 */
	public void updateLevelDisplay(int level){
		this.levelDisplay.setText(""+level);
	}
	
	/**
	 * Game is over; bring up display for end game options
	 */
	static void endGame()
	{
		GameRunner.gameOver = true;
		
		try {
			GameRunner.startMarch();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		lockout = true;
		layeredContainer.moveToFront(gameoverPanel);
		gameoverPanel.setVisible(true);
		GameRunner.pauseGame(true);
	}
	
	/**
	 * pause game
	 */
	static void pause()
	{		
		if(pausestate==false & lockout==false){
			layeredContainer.moveToFront(pausePanel);
			pausePanel.setVisible(true);
			updateSassyVader("Pause");
			pausestate = true;
		}
		else if (pausestate==true & lockout==false){
			pausePanel.setVisible(false);
			updateSassyVader("Default");
			pausestate = false;
		}
		GameRunner.pauseGame(pausestate);
	}
	
	/**
	 * Create containers and initial values for display components
	 */
	private void initialize() {
		lockout = false;
		pausestate = false;
    	
		/*
		 * Generates the frame
		 */
		frame = new JFrame("STAR TETRIS");
		JLabel background = null;
		try{
			/* Loads the space background image */
			background = new JLabel(new ImageIcon(ImageIO.read(new File("StarWarsSpace.png"))));
			//Image Source: https://pixabay.com/static/uploads/photo/2016/01/27/15/25/space-1164579_960_720.png
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* Sets the space background */
		frame.setContentPane(background);
		
		/* Sets coordinates of upper left corner of window. */
		frame.setBounds(0, 0, 1, 1);
		Dimension d = new Dimension(1000, 600);
		frame.setMinimumSize(d);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		
		/* Creates all the containers for objects on the screen */
		BorderLayout tetrisLayout = new BorderLayout();
		JPanel gameplayScreen = new JPanel();
		JPanel welcomeScreen = new JPanel();	
		pausePanel = new JPanel();
		gameoverPanel = new JPanel();
		JPanel gameoverScreen = new JPanel();
		JPanel leftPanel = new JPanel(new GridLayout(3,1,0,20));
		JPanel rightPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		leftPanel.setOpaque(false);				/* .setOpaque(true), then will not be transparent */
		rightPanel.setOpaque(false);
		bottomPanel.setOpaque(false);
		gameplayScreen.setOpaque(false);
		welcomeScreen.setOpaque(false);
		pausePanel.setOpaque(false);
		gameoverScreen.setOpaque(false);
		
		
		/*
		 * Generates welcome screen
		 */
		ImageIcon welcomeicon = new ImageIcon(new ImageIcon("StarTetris.png").getImage().getScaledInstance(1000,451, Image.SCALE_DEFAULT));
		JLabel welcomebackground = new JLabel ("",welcomeicon,JLabel.CENTER);
		welcomeScreen.add(welcomebackground);
			
		JButton startButton = new JButton("Start Game");
		startButton.setFont(new Font("Courier New", Font.BOLD, 12));
		startButton.setForeground(Color.BLACK);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screens.show(screensContainer,"Gameplay");
				GameRunner.pauseGame(false);
				GameRunner.stopMusic();
			}
		});
		welcomeScreen.add(startButton,BorderLayout.SOUTH);
		
		
		/*
		 * Generates tetris panel aka game board
		 */
		tetrisPanel = new JPanel(new GridLayout(28,14,1,1));
		tetrisPanel.setBackground(Color.BLACK);
		playgrid = new JLabel[28][14];
		try{
			for(int i=0; i<playgrid.length;i++){
				for( int j=0; j<playgrid[i].length; j++){
					playgrid[i][j] = new JLabel();
					playgrid[i][j].setIcon(new ImageIcon(" "));
					tetrisPanel.add(playgrid[i][j]);
				}				
			}
		} catch (IndexOutOfBoundsException e) {
    		e.printStackTrace();
    	}
		
     
		/*
		 * Generates sassyvader panel
		 */
		//Image Source: http://vignette3.wikia.nocookie.net/starwars/images/9/96/Death_star_blueprints_sw_card_trader.png/revision/latest?cb=20160620042622
		vadericon = new ImageIcon(new ImageIcon("DeathStarBlueprintWide.png").getImage().getScaledInstance(256,164, Image.SCALE_DEFAULT));
		sassyvader = new JLabel ("",vadericon,JLabel.CENTER);
		sassyvaderPanel = new JPanel();
		sassyvaderPanel.setBackground(Color.BLACK);
		sassyvaderPanel.setOpaque(false);
		sassyvaderPanel.add(sassyvader,BorderLayout.CENTER);

		/*
		 * Generates control panel
		 */
		controlPanel = new JPanel(new GridLayout(3,3,1,1));
		controlPanel.setBackground(Color.BLACK);
		controlPanel.setOpaque(true);
		JButton pauseButton = new JButton("Pause");
		pauseButton.setFont(new Font("Courier New", Font.BOLD, 12));
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pause();
			}
		});
		
		JButton quitButton = new JButton("Quit");
		quitButton.setFont(new Font("Courier New", Font.BOLD, 12));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Quit Game
				if(lockout==false){
					updateSassyVader("Quit");
					endGame();
					
				}
				//otherwise do nothing
			}
		});
		
		/* Adds the components to the control panel. */
		JLabel levelLabel = new JLabel(" Level:");
		levelLabel.setFont(new Font("Courier New", Font.BOLD, 16));
		levelLabel.setForeground(Color.WHITE);
		
		JLabel scoreLabel = new JLabel(" Score:");
		scoreLabel.setFont(new Font("Courier New", Font.BOLD, 16));
		scoreLabel.setForeground(Color.WHITE);
		
		scoreDisplay = new JLabel("0");
		scoreDisplay.setFont(new Font("Courier New", Font.BOLD, 16));
		scoreDisplay.setForeground(Color.WHITE);
		
		levelDisplay = new JLabel("1");
		levelDisplay.setFont(new Font("Courier New", Font.BOLD, 16));
		levelDisplay.setForeground(Color.WHITE);
		
		controlPanel.add(pauseButton);
		controlPanel.add(quitButton);
		
		controlPanel.add(levelLabel);		
		controlPanel.add(levelDisplay);
		controlPanel.add(scoreLabel);
		controlPanel.add(scoreDisplay);
		
		
		/*
		 * Generates preview panel. Works the same way as the tetromino grid display.
		 */		
		previewPanel = new JPanel();
		previewPanel.setBackground(Color.BLACK);
		previewPanel.setOpaque(true);
		
		JLabel nextLabel = new JLabel(" Next:");
		nextLabel.setFont(new Font("Courier New", Font.BOLD, 16));
		nextLabel.setForeground(Color.WHITE);
		previewPanel.add(nextLabel, BorderLayout.LINE_START);
		
		JPanel previewContainer = new JPanel(new GridLayout(8,6,1,1));
		previewContainer.setOpaque(false);
		previewPanel.add(previewContainer, BorderLayout.CENTER);

		previewgrid = new JLabel[8][6];
		try{
			for(int i=0; i<previewgrid.length;i++){
				for( int j=0; j<previewgrid[i].length; j++){
					previewgrid[i][j] = new JLabel();
					previewgrid[i][j].setIcon(new ImageIcon(" "));
					previewContainer.add(previewgrid[i][j]);
				}				
			}
		} catch (IndexOutOfBoundsException e) {
    		e.printStackTrace();
    	}
		
		/*
		 * Generates pause panel.
		 */
		ImageIcon pauseicon = new ImageIcon(new ImageIcon("Pause.png").getImage().getScaledInstance(300,129,Image.SCALE_DEFAULT));
		//Image Source: http://static.comicvine.com/uploads/original/10/109662/4534084-death_star_ii2.jpg
		JLabel pauselabel = new JLabel ("",pauseicon,JLabel.CENTER);
		pausePanel.add(pauselabel,BorderLayout.CENTER);
		pausePanel.setLocation(d.width/3,d.height/3+200);
		pausePanel.setSize(300,200);
		pausePanel.setVisible(false);

		/*
		 * Generates quit panel.
		 */
		ImageIcon quiticon = new ImageIcon(new ImageIcon("GameOver.png").getImage().getScaledInstance(300,129,Image.SCALE_DEFAULT));
		//Image Source: http://static.comicvine.com/uploads/original/10/109662/4534084-death_star_ii2.jpg
		JLabel quitlabel = new JLabel ("",quiticon,JLabel.CENTER);
		gameoverPanel.add(quitlabel,BorderLayout.CENTER);
		gameoverPanel.setLocation(d.width/3,d.height/3+200);
		gameoverPanel.setSize(300,200);
		gameoverPanel.setVisible(false);
		
		JButton playagainButton = new JButton("Play Again");
		playagainButton.setFont(new Font("Courier New", Font.BOLD, 12));
		playagainButton.setForeground(Color.BLACK);
		playagainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				GameRunner.restartGame();
			}
		});
		
		JButton quitgameButton = new JButton("Quit");
		quitgameButton.setFont(new Font("Courier New", Font.BOLD, 12));
		quitgameButton.setForeground(Color.BLACK);
		quitgameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameRunner.endGame();
			}
		});
		
		gameoverPanel.add(playagainButton, BorderLayout.SOUTH);
		gameoverPanel.add(quitgameButton, BorderLayout.SOUTH);
		gameoverPanel.setBackground(Color.BLACK);
		
	
		/*
		 * Combines all containers and panels. 
		 */
	    frame.setLayout(tetrisLayout);  
	    
		/* this is the part that makes changing screens possible */
		screens = new CardLayout();
		screensContainer = new JPanel(screens);
		screensContainer.setOpaque(false);
		screensContainer.add(welcomeScreen, "Welcome");
		screensContainer.add(gameplayScreen, "Gameplay");
		
		/* this is the part that makes the popups possible */
		layeredContainer = new JLayeredPane();
		layeredContainer.setPreferredSize(d);
		screensContainer.setLocation(0,0);
		screensContainer.setSize(d);
		layeredContainer.add(screensContainer, new Integer(0));
		layeredContainer.add(pausePanel, new Integer(0));
		layeredContainer.add(gameoverPanel, new Integer(0));
		layeredContainer.setOpaque(false);
		add(layeredContainer);
		
	    frame.add(layeredContainer, BorderLayout.CENTER);
	    
		gameplayScreen.add(leftPanel, BorderLayout.WEST);
		gameplayScreen.add(rightPanel, BorderLayout.CENTER);
		gameplayScreen.add(bottomPanel, BorderLayout.SOUTH);
        rightPanel.add(tetrisPanel);
        leftPanel.add(sassyvaderPanel);
        leftPanel.add(controlPanel);
        leftPanel.add(previewPanel);
    
        /* stuff you always do to frame */
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);	
	}
	
	/**
	 * Accessor for tetris panel used by gamerunner
	 * @return tetrisPanel The main JPanel for tetris to attach keybinding to
	 */
	public JPanel getPanel()
	{
		return tetrisPanel;
	}
}

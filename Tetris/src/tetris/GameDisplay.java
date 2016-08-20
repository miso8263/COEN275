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
import javax.swing.*;

/*import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;*/

/**
 * Class for displaying game elements to the screen.  
 * 
 * This will be driven by the GameRunner with data retrieved from GameSystem
 * 
 * This is going to have some fields but we haven't worked that out yet
 */
public class GameDisplay extends JComponent{
	//TODO: add fields, such as String flavorText, actual display, etc.
	
	static JFrame frame;
	
	static JPanel tetrisPanel;
	static JPanel controlPanel;
	static JPanel sassyvaderPanel;
	static JPanel screensContainer;
	static CardLayout screens;
	static JLabel sassyvader;
	static JPanel previewPanel;
	static JLabel[][] playgrid;
	static JLabel[][] previewgrid;
	
	static ImageIcon vadericon;
	
	JLabel scoreDisplay;
	JLabel levelDisplay;
	
	private java.awt.Image image;
	
	public void ImagePanel(java.awt.Image image){
		this.image = image;
	}
	
	/**
	 * Default constructor
	 * initialize default display
	 */
	public GameDisplay(){
		
		// Game board is 22 blocks high by 10 blocks wide
		// Game board has a border
		initialize();
		displayScreen("gameplayScreen");
		//screens.
		System.out.println("constructor complete");
	}
	
	
	/**
	 * Update display
	 * @param gridData
	 * gridData is a boolean grid with the same dimensions as the display
	 * 0 means no block present
	 * 1 means block present
	 */
	public static void updateGridDisplay(boolean[][] gridData){
		BufferedImage mino = null;
		
		ImageIcon minoIcon = new ImageIcon(new ImageIcon("Mino.png").getImage().getScaledInstance(19, 19, Image.SCALE_DEFAULT));
		JLabel minoLabel = new JLabel(minoIcon);
		
		for (int i = 0; i < gridData.length-2; i++) {	
	    {
	    	for (int j = 0; j < gridData[0].length; j++)
	            {
	                if (gridData[i][j])
	                {
	                	playgrid[i][j].setIcon(minoIcon);
	                }  
	                else
	                {
	                	playgrid[i][j].setIcon(null);
	                }
	            }
	        }	    	
		}
		frame.setVisible(true);
	}
	
	/**
	 * Update game message (pause, quit, etc)
	 * @param text
	 */
	public static void updateGameMessage(String text){
		
	}
	
	/**
	 * Update sassy vader image (pause, quit, etc)
	 * @param int 1 = pause, 2 = endgame (quit or death), 3 = four
	 */
	public static void updateSassyVader(String event){
		
		Image vaderimage;
	
		if (event == "Pause"){
				vaderimage = new ImageIcon("VaderPause.png").getImage().getScaledInstance(256,164, Image.SCALE_DEFAULT);
				vadericon.setImage(vaderimage);
				sassyvader.revalidate();
				sassyvader.repaint();
				sassyvader.update(sassyvader.getGraphics());
		}
		else if (event == "Game Over"){
				vaderimage = new ImageIcon("VaderDeath.png").getImage().getScaledInstance(256,164, Image.SCALE_DEFAULT);
				vadericon.setImage(vaderimage);
				sassyvader.revalidate();
				sassyvader.repaint();
				sassyvader.update(sassyvader.getGraphics());	
		}
		else if (event == "Four"){
				vaderimage = new ImageIcon("VaderFour.png").getImage().getScaledInstance(256,164, Image.SCALE_DEFAULT);
				vadericon.setImage(vaderimage);
				sassyvader.revalidate();
				sassyvader.repaint();
				sassyvader.update(sassyvader.getGraphics());	
		}
		else {
			vaderimage = new ImageIcon("DeathStarBlueprintWide.png").getImage().getScaledInstance(256,164, Image.SCALE_DEFAULT);
		}
		


	}
	
	/**
	 * Update preview of next shape to come
	 * @param next tetromino  -> I propose we change this to a boolean[][] previewData
	 * to be consistent with the handling of the tetromino game board. 
	 */
	public static void updatePreview(boolean[][] previewData){
		BufferedImage mino = null;
		
		ImageIcon minoIcon = new ImageIcon(new ImageIcon("Mino.png").getImage().getScaledInstance(19, 19, Image.SCALE_DEFAULT));
		JLabel minoLabel = new JLabel(minoIcon);
		
		for (int i = 0; i < previewData.length; i++) {	
		
	    	for (int j = 0; j < previewData[0].length; j++) {
	                if (previewData[i][j])
	                {
	                	previewgrid[i][j].setIcon(minoIcon);
	                }              
	        }
	    }	    	
		
		frame.setVisible(true);
	}
	
	/**
	 * Display score
	 * @param score
	 */
	public void updateScoreDisplay(int score){
	//	this.scoreDisplay.setName(score);
		
	}
	
	private void initialize() {
		
    	
		/**
		 * Generates the frame
		 */
		frame = new JFrame("STAR TETRIS");
		JLabel background = null;
		try{
			/* Loads the space background image */
			background = new JLabel(new ImageIcon(ImageIO.read(new File("StarWarsSpace.png"))));
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
		JPanel pauseScreen = new JPanel();
		JPanel gameoverScreen = new JPanel();
		JPanel leftPanel = new JPanel(new GridLayout(3,1,0,20));
		JPanel rightPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		leftPanel.setOpaque(false);				/* .setOpaque(true), then will not be transparent */
		rightPanel.setOpaque(false);
		bottomPanel.setOpaque(false);
		gameplayScreen.setOpaque(false);
		welcomeScreen.setOpaque(false);
		pauseScreen.setOpaque(false);

		
		/** 
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
		
     
		/** 
		 * Generates sassyvader panel
		 */
		vadericon = new ImageIcon(new ImageIcon("DeathStarBlueprintWide.png").getImage().getScaledInstance(256,164, Image.SCALE_DEFAULT));
		sassyvader = new JLabel ("",vadericon,JLabel.CENTER);
		sassyvaderPanel = new JPanel();
		sassyvaderPanel.setBackground(Color.BLACK);
		sassyvaderPanel.setOpaque(false);
		sassyvaderPanel.add(sassyvader,BorderLayout.CENTER);

		/** 
		 * Generates control panel
		 */
		controlPanel = new JPanel(new GridLayout(3,3,1,1));
		controlPanel.setBackground(Color.BLACK);
		controlPanel.setOpaque(true);
		JButton pauseButton = new JButton("Pause");
		pauseButton.setFont(new Font("Courier New", Font.BOLD, 12));
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Pause Game - needs to be added
				//stop timer
				//update game message
				displayScreen("pauseScreen");
			}
		});
		
		JButton quitButton = new JButton("Quit");
		quitButton.setFont(new Font("Courier New", Font.BOLD, 12));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Quit Game
				//updateSassyVader(2);
				displayScreen("gameoverScreen");
				//System.exit(0); //this works!
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
		
		
		/** 
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
		
		
		/** 
		 * Combines all containers and panels. 
		 */
	    frame.setLayout(tetrisLayout);  
	    
		/* this is the part that makes changing screens possible */
		screens = new CardLayout();
		screensContainer = new JPanel(screens);
		screensContainer.setOpaque(false);
		//screensContainer.add(welcomeScreen);
		screensContainer.add(gameplayScreen);
		screensContainer.add(pauseScreen);
		screensContainer.add(gameoverScreen);
		
	    frame.add(screensContainer, BorderLayout.CENTER);
		gameplayScreen.add(leftPanel, BorderLayout.WEST);
		gameplayScreen.add(rightPanel, BorderLayout.CENTER);
		gameplayScreen.add(bottomPanel, BorderLayout.SOUTH);
        rightPanel.add(tetrisPanel);
        leftPanel.add(sassyvaderPanel);
        leftPanel.add(controlPanel);
        leftPanel.add(previewPanel);
        
    
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        
	
	}
	
	private void displayScreen (String screenname){
		screens.show(screensContainer, screenname);
	}
}

package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	static JPanel previewPanel;
	static JLabel[][] playgrid;
	static JLabel[][] previewgrid;
	
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
	public static void updateScoreDisplay(int score){
		
	}
	
	private void initialize() {
		
    	
		/**
		 * Generates the frame
		 */
		frame = new JFrame("STAR TETRIS");
		JLabel background = null;
		try{
			background = new JLabel(new ImageIcon(ImageIO.read(new File("StarWarsSpace.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* Sets the space background */
		frame.setContentPane(background);
		
		/*Places the window in the center of the screen(ish) */
		frame.setBounds(400, 200, 1, 1);
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		
		/* Creates a layout to hold all the panels, and three major panels to hold
		 * the various sub-panels that will be laid out. */
		BorderLayout tetrisLayout = new BorderLayout();
		JPanel leftPanel = new JPanel(new GridLayout(3,1,0,10));
		JPanel rightPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		leftPanel.setOpaque(false);				/* .setOpaque(true), then will be transparent */
		rightPanel.setOpaque(false);
		bottomPanel.setOpaque(false);

		/* Combines all the panels. */
	    frame.setLayout(tetrisLayout);  
		frame.add(leftPanel, BorderLayout.WEST);
		frame.add(rightPanel, BorderLayout.CENTER);
		frame.add(bottomPanel, BorderLayout.SOUTH);
       
		
		/* generates the tetris panel */
		tetrisPanel = new JPanel(new GridLayout(20,10,1,1));
		tetrisPanel.setBackground(Color.BLACK);
		playgrid = new JLabel[20][10];
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
		ImageIcon defimage = new ImageIcon(new ImageIcon("DeathStarBlueprint.png").getImage().getScaledInstance(150,120, Image.SCALE_DEFAULT));
		JLabel sassyvaderdefault = new JLabel ("",defimage,JLabel.CENTER);
		sassyvaderPanel = new JPanel();
		sassyvaderPanel.setBackground(Color.BLACK);
		sassyvaderPanel.setOpaque(false);
		sassyvaderPanel.add(sassyvaderdefault,BorderLayout.CENTER);

		/** 
		 * Generates preview panel
		 */
		controlPanel = new JPanel(new GridLayout(3,2,1,1));
		controlPanel.setBackground(Color.BLACK);
		controlPanel.setOpaque(true);
		JButton pauseButton = new JButton("Pause");
		pauseButton.setFont(new Font("Courier New", Font.BOLD, 12));
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Pause Game - needs to be added
			}
		});
		
		JButton quitButton = new JButton("Quit");
		quitButton.setFont(new Font("Courier New", Font.BOLD, 12));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Quit Game
				System.exit(0); //this works!
			}
		});
		
		/* Adds the components to the control panel. */
		JLabel levelLabel = new JLabel("  Level:");
		levelLabel.setFont(new Font("Courier New", Font.BOLD, 16));
		levelLabel.setForeground(Color.WHITE);
		
		JLabel scoreLabel = new JLabel("  Score:");
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
		previewPanel = new JPanel(new GridLayout(8,8,1,1));
		previewPanel.setBackground(Color.BLACK);
		previewPanel.setOpaque(true);
		previewgrid = new JLabel[8][8];
		try{
			for(int i=0; i<previewgrid.length;i++){
				for( int j=0; j<previewgrid[i].length; j++){
					previewgrid[i][j] = new JLabel();
					previewgrid[i][j].setIcon(new ImageIcon(" "));
					previewPanel.add(previewgrid[i][j]);
				}				
			}
		} catch (IndexOutOfBoundsException e) {
    		e.printStackTrace();
    	}
		
		
		/** 
		 * Add tetrisPanel panel, sassyvaderPanel, controlPanel, and previewPane to \
		 * appropriate spots in frame.
		 */
        rightPanel.add(tetrisPanel);
        leftPanel.add(sassyvaderPanel);
        leftPanel.add(controlPanel);
        leftPanel.add(previewPanel);

        
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
	
	}
}

package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

//import com.sun.medialib.mlib.Image;

/**
 * Class for displaying game elements to the screen.  
 * 
 * This will be driven by the GameRunner with data retrieved from GameSystem
 * 
 * This is going to have some fields but we haven't worked that out yet
 */
public class GameDisplay extends JComponent{
	//TODO: add fields, such as String flavorText, actual display, etc.
	
	JFrame frame;
	JTextField textField_1;
	JTextField textField;
	
	private java.awt.Image image;
	
	public void ImagePanel(java.awt.Image image){
		this.image = image;
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
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
		try {
		    mino = ImageIO.read(new File("Mino.png"));
		} catch (IOException e) {
		}
	
		for (int i = 0; i < gridData.length - 2; i++) {
		
	    {
	    	for (int j = 0; j < gridData[0].length; j++)
	            {
	                if (gridData[i][j])
	                {
	                    
	                }
	                else
	                {
	                    
	                }
	            }
	        }
		}
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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 490);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try{
			frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("StarWarsSpace.png")))));
		} catch (IOException e) {
    		e.printStackTrace();
    	}
		frame.pack();
    	frame.setVisible(true);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		
		Box horizontalBox = Box.createHorizontalBox();
		
		
	//panel with the labels creating components
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(horizontalBox, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addGap(39))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
								.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
							.addGap(18)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
					.addGap(21))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(horizontalBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		//button for pause game
		JButton btnNewButton_1 = new JButton("Pause Game");
		btnNewButton_1.setFont(new Font("Courier New", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		//button for quit game
		JButton btnNewButton = new JButton("Quit Game");
		btnNewButton.setFont(new Font("Courier New", Font.BOLD, 12));
		
		//button for score
		JLabel lblNewLabel = new JLabel("Score");
		lblNewLabel.setFont(new Font("Courier New", Font.BOLD, 14));
		
		
		//label for level
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setFont(new Font("Courier New", Font.BOLD, 14));
		
		//text fields for sacore and levels
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		
		//adding components to the Panel with the buttons
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLevel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(43)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(24))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(5)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLevel)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		panel_2.setLayout(gl_panel_2);
		frame.getContentPane().setLayout(groupLayout);
	}
}

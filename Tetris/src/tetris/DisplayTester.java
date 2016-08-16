package tetris;

import java.awt.EventQueue;

public class DisplayTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				boolean[][] testgridData = {{false, false, true, false, false, false, false, false, false, false},
											{false, false, true, false, false, false, false, false, false, false},
											{false, false, true, false, false, false, false, false, false, false},
											{false, false, true, false, false, false, false, false, false, false},
											{false, false, false, false, false, false, false, false, false, false},
											{false, false, false, true, true, false, false, false, false, false},
											{false, false, false, false, true, true, false, false, false, false},
											{false, false, false, false, false, false, false, false, false, false},
											{false, false, false, false, false, false, false, false, false, false},
											{false, false, false, false, false, false, false, false, false, false},
											{false, false, false, false, false, false, false, false, false, false},
											{false, false, false, false, false, false, false, false, false, false},
											{false, false, false, false, false, false, false, false, false, false},
											{false, false, false, false, false, false, false, false, false, false},
											{false, false, false, false, false, false, false, false, false, false},
											{false, false, false, false, false, false, false, false, false, false},
											{false, false, false, false, false, false, false, false, false, false},
											{false, false, false, false, false, false, false, false, false, false},
											{false, false, false, false, false, false, false, false, false, false},
											{false, false, false, false, false, false, true, true, false, false},
											{false, false, false, false, false, false, false, true, false, false},
											{false, false, false, false, false, false, false, true, false, false}};
				
				boolean[][] testpreviewData = {{false, false, false, false, false, false, false, false},
											   {false, false, false, false, false, false, false, false},
											   {false, false, false, false, false, false, false, false},
											   {false, false, false, true, true, false, false, false},
											   {false, false, true, true, false, false, false, false},
											   {false, false, false, false, false, false, false, false},
											   {false, false, false, false, false, false, false, false},
											   {false, false, false, false, false, false, false, false}};
				
				GameDisplay window = new GameDisplay();
				try {
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				window.updateGridDisplay(testgridData);
				window.updatePreview(testpreviewData);
			}
		});
	}

}

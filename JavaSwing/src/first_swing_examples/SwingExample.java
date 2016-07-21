package first_swing_examples;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SwingExample implements Runnable{
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		JFrame f = new JFrame("Hello, !");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new FlowLayout());
		f.add(new JLabel("Hello, world!"));
		f.add(new JButton("Press me!"));
		
		f.pack();
		f.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingExample se = new SwingExample();
		se.run();
	}

	

}

package first_swing_examples;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SecondExample extends JFrame{
	private final JButton b = new JButton();
	
	public SecondExample(){
		super();
		this.setTitle("Hello");
	}
	
	public static void main(String[] args) {
		new SecondExample();
	}

}

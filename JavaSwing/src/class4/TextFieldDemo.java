package class4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextFieldDemo extends JFrame implements ActionListener {

	//Class Declarations
	JTextField tf1, tf2;	
	
	//Constructor
	public TextFieldDemo() {
		super("TextField  Demo");
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		tf1 = new JTextField(10);
		
		tf2 = new JTextField(10);
		tf2.setEditable(false);	// user cannot enter input in this field
		container.add(tf1);
		container.add(tf2);
	
		tf1.addActionListener(this);
	
		setSize(325, 100);
		setVisible(true);
	}

		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == tf1) {
				String input = ((JTextField)e.getSource()).getText();
				tf2.setText(input.toUpperCase());
			}
	}
	//Main Program that starts Execution
	public static void main(String args[]) {
		TextFieldDemo test = new TextFieldDemo();
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}


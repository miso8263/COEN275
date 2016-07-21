package class4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BorderWindow extends JFrame {
     
    public BorderWindow() {
	
		setTitle("BorderLayout");
		Container contentPane = getContentPane();
        contentPane.add(new JButton("Button 1 (NORTH)"),
                        BorderLayout.NORTH);
        contentPane.add(new JButton("2 (CENTER)"),
                        BorderLayout.CENTER);
        contentPane.add(new JButton("Button 3 (WEST)"),
                        BorderLayout.WEST);
        contentPane.add(new JButton("Long-Named Button 4 (SOUTH)"),
                        BorderLayout.SOUTH);
        contentPane.add(new JButton("Button 5 (EAST)"),
                        BorderLayout.EAST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    
		pack();
		setVisible(true);
    }

    public static void main(String args[]) {
        BorderWindow window = new BorderWindow();
    }
}
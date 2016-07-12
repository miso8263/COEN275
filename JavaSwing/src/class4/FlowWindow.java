package class4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FlowWindow extends JFrame {
     
    public FlowWindow() {
    	setTitle("FlowLayout");
    	
        Container contentPane = getContentPane();
        
        // FlowLayout (int align, int hgap, int vgap);
   		contentPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        
		contentPane.add(new JButton("Button 1"));
        contentPane.add(new JButton("BT2"));
        contentPane.add(new JButton("Button 3"));
        contentPane.add(new JButton("Long-Named Button 4"));
        contentPane.add(new JButton("Button 5"));

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
    }
     public void actionPerformed( ActionEvent event ){
         JOptionPane.showMessageDialog( null,
            "You pressed: " + event.getActionCommand() );
    }
    public static void main(String args[]) {
        FlowWindow window = new FlowWindow();
      
    }
}
package class4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FlowWindow extends JFrame {
	JButton a = new JButton("Nancy");
	JButton b = new JButton("Tracy");
	JButton c = new JButton("Kelly");
	JButton d = new JButton("Bindy");
	JButton e = new JButton("Aiimy");
     
    public FlowWindow() {
    	setTitle("Pick a lady");
    	
        Container contentPane = getContentPane();
        
        // FlowLayout (int align, int hgap, int vgap);
   		contentPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 30)); //alignment, horizontal gap, vertical gap
        
   		JPanel GeorgePanel = new JPanel();
   		/*
   		//example of creating buttons in a loop
        JButton[] buttlist = new JButton[5];
        
        for(int i = 0; i < 5; i++){
        	buttlist[i] = new JButton();
        	buttlist[i].addActionListener(new ActionListener(){
       			public void actionPerformed(ActionEvent e){
       				System.out.println("You pressed button");
       			}
       		});
        	GeorgePanel.add(buttlist[i]);
        	
        }
        */
        
   		GeorgePanel.add(a);
   		a.addActionListener(new ActionListener(){
   			public void actionPerformed(ActionEvent e){
   				System.out.println("You die.");
   			}
   		});
   		GeorgePanel.add(b);
   		b.addActionListener(new ActionListener(){
   			public void actionPerformed(ActionEvent e){
   				System.out.println("You fall in love but she's just not that into you.");
   			}
   		});
   		GeorgePanel.add(c);
   		c.addActionListener(new ActionListener(){
   			public void actionPerformed(ActionEvent e){
   				System.out.println("She is a unicorn.");
   			}
   		});
   		GeorgePanel.add(d);
   		d.addActionListener(new ActionListener(){
   			public void actionPerformed(ActionEvent e){
   				System.out.println("You win.");
   			}
   		});
   		GeorgePanel.add(e);
   		e.addActionListener(new ActionListener(){
   			public void actionPerformed(ActionEvent e){
   				System.out.println("She is a cylon.");
   			}
   		});
   		
   		contentPane.add(GeorgePanel);
        
        setLocationRelativeTo(null); //moves it to the exact center of the screen
        
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
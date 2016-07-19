package pizza;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheesePizza implements Pizza {	
	JLabel myLabel;
	
	@Override
	public int getCost(){
		return 1;
	}
	
	@Override
	public JPanel drawPizza()
	{
		myLabel = new JLabel();
		ImageIcon myImage = new ImageIcon("http://lanakrcole.com/pizza_houze/images/crust.png");
		myLabel.setIcon(myImage);
		
		JPanel myPanel = new JPanel();
		myPanel.add(myLabel);
		
		return myPanel;
	}
}

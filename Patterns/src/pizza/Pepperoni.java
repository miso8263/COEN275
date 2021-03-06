package pizza;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pepperoni extends PizzaDecorator {
	JLabel myLabel;
	
	public Pepperoni(Pizza pizza)
	{
		super(pizza);
	}
	
	@Override
	public int getCost()
	{
		return super.getCost() + 1;
	}
	
	@Override
	public JPanel drawPizza()
	{
		myLabel = new JLabel();
		ImageIcon myImage = new ImageIcon("http://lanakrcole.com/pizza_houze/images/pepperoni.png");
		myLabel.setIcon(myImage);
		
		return super.drawPizza().add(myLabel);
	}

}

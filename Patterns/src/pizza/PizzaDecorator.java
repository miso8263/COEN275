package pizza;

import java.awt.Graphics;

import javax.swing.JPanel;

public class PizzaDecorator implements Pizza {
	protected Pizza decoratedPizza;
	
	public PizzaDecorator(Pizza decoratedPizza)
	{
		this.decoratedPizza = decoratedPizza;
	}
	
	public int getCost()
	{
		return decoratedPizza.getCost();
	}
	
	public JPanel drawPizza()
	{
		return decoratedPizza.drawPizza();
	}
}

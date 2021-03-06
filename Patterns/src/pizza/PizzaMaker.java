package pizza;

import java.awt.Graphics;

import javax.swing.JFrame;

public class PizzaMaker {
	
	public static void printCost(Pizza p)
	{
		System.out.println("My pizza costs $"+p.getCost());
	}
	
	public static void main(String[] args){
		
		JFrame myFrame = new JFrame();
		
		Pizza myPizza = new CheesePizza();		
		
		myPizza = new Pepperoni(myPizza);
		
		myPizza = new Olives(myPizza);
		
		printCost(myPizza);
		
		myFrame.add(myPizza.drawPizza());
		
		myFrame.setVisible(true);
		

	}
}

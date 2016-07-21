package tetris;

import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Class for managing game clock and related activities. 
 * Extends the existing timer in java swing
 * http://docs.oracle.com/javase/6/docs/api/javax/swing/Timer.html
 *
 */
public class Timekeeper extends Timer{
	private int time;
	private int speed;
	private ActionListener listener;
	
	
	/**
	 * Default constructor
	 * Initialize timekeeper object variables
	 * Return a timekeeper object for handling game clock
	 */
	public Timekeeper(){
		super(0, null);
		this.speed = 0;
		this.listener = null;
	}
	
	/**
	 * 
	 * @param _speed
	 * @param _listener
	 * Initialize timekeeper object variables
	 * Return a timekeeper object for handling game clock
	 */
	public Timekeeper(int _speed, ActionListener _listener){
		super(_speed, _listener);
		this.speed = _speed;
		this.listener = _listener;
	}
	
	/**
	 * @return speed
	 */
	public static int getSpeed(){
		return -1; //temp value
	}
	
	/**
	 * set value of speed
	 * @param newSpeed
	 */
	public static void setSpeed(int newSpeed){
		
	}
}

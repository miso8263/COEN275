package tetris;

import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.Timer;

/**
 * Class for managing game clock and related activities. 
 * Extends the existing timerTask in java swing
 * http://docs.oracle.com/javase/6/docs/api/javax/swing/Timer.html
 *
 */
public class Timekeeper extends TimerTask{
	private int time;
	private int speed;
	private GameSystem localSystem;
	
	/**
	 * Default constructor
	 * Initialize timekeeper object variables
	 * Return a timekeeper object for handling game clock
	 */
	public Timekeeper(){
	this.localSystem=null;
		this.speed = 0;
		
	}
	
	/**
	 * 
	 * @param _speed
	 * @param _ge
	 * Initialize timekeeper object variables
	 * Return a timekeeper object for handling game clock
	 */
	public Timekeeper(int _speed, GameSystem ge){
		
		this.localSystem = ge;
		this.speed = _speed;
	}
	
	public void run(){
		localSystem.moveActiveTetromino(0, -1, 0);
	}
	
	
	/**
	 * @return speed
	 */
	public int getSpeed(){
		return this.speed; //temp value
	}
	
	/**
	 * set value of speed
	 * @param newSpeed
	 */
	public void setSpeed(int newSpeed){
	this.speed= newSpeed;	
	}
}

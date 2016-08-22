package tetris;

import java.util.TimerTask;

/**
 * Class for managing game clock and related activities. 
 * Extends the existing timerTask in java swing
 * http://docs.oracle.com/javase/6/docs/api/javax/swing/Timer.html
 *
 */
public class Timekeeper extends TimerTask{
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
	public Timekeeper(int _speed, GameSystem _ge){
		
		this.localSystem = _ge;
		this.speed = _speed;
	}
	
	/**
	 * run
	 * For attaching movement to timer
	 * Move tetromino up one space
	 */
	public void run(){
		localSystem.moveActiveTetromino(0, -1, 0);
	}
	
	
	/**
	 * getSpeed
	 * @return speed
	 */
	public int getSpeed(){
		return this.speed;
	}
	
	/**
	 * set value of speed
	 * @param newSpeed
	 */
	public void setSpeed(int newSpeed){
		this.speed = newSpeed;	
	}
}

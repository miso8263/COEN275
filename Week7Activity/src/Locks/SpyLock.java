package Locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.TimeUnit;

public class SpyLock implements Lock {
	boolean theLockIsFuckingBusy;
	
	public void lock(){
		//System.out.println("Lock is now locked");
		theLockIsFuckingBusy = true;
	}
	
	public void lockInterruptibly(){
		
	}
	
	public boolean tryLock(){
		
		return !theLockIsFuckingBusy;
	}
	
	public boolean tryLock(long time,  TimeUnit unit) {
		return !theLockIsFuckingBusy;
	}
	
	public void unlock(){
		//System.out.println("Lock is now unlocked");
		theLockIsFuckingBusy = false;
	}
	
	public Condition newCondition(){
		return null; 
	}
	
	
}

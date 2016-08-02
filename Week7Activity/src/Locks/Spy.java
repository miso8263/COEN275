package Locks;

import java.util.Random;
import java.util.concurrent.locks.Lock;


public class Spy implements Runnable {
	int number;
	private String greeting;
	Phonebooth myPhonebooth; 
	SpyLock loo;

	private static final int REPETITIONS = 10;
	private static final int DELAY = 100;
	
	public Spy(int number, Phonebooth thephonebooth, SpyLock l) {
		this.number = number;
		myPhonebooth = thephonebooth;
		loo = l;
	}
	
	public void run() {
		while(!loo.tryLock())
		{
			
		}
		loo.lock();
		System.out.println("Lock is now locked");
		try {
			Random myrand = new Random();
			int no, x;
			for (int i = 1; i <= REPETITIONS; i++) {
				no = myrand.nextInt(10);
				if (no < 5){
					
					myPhonebooth.addCaller(number);
					System.out.println("Agent 00" + number +" adding 00"+ number + " to queue");
				}
				else {
					x = myPhonebooth.removeCaller();
					System.out.println("Agent 00" + number + " removed 00" + x +" from queue");
				}
				
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException exception) {
		}
		loo.unlock();
		System.out.println("Lock is now unlocked");
	}
}

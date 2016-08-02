package Locks;

/**
 * This program runs two threads in parallel.
 */
public class ThreadTester {
	public static void main(String[] args) {
		Phonebooth testPhonebooth = new Phonebooth();
		SpyLock testSpyLock = new SpyLock();
		
		Runnable r1 = new Spy(7,testPhonebooth, testSpyLock);
		Runnable r2 = new Spy(5,testPhonebooth, testSpyLock);

		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);

		t1.start();
		t2.start();
		
	}
}

package Locks;

import java.util.ArrayList;


public class Phonebooth {

	int[] callerNo;
	int size;
		
	public Phonebooth (){
		callerNo = new int[9];
		size = 0;
	}
	
	public boolean isFull(){
		if (size == callerNo.length) {
			return true;
		}
		return false;
	}

	
	public boolean isEmpty(){
		if (size == 0){
			return true;
		}
		return false;
	}
	
	public void addCaller(int newCaller){
		if (isFull()){
			return;
		}
		callerNo[size] = newCaller;
		size++;
	}
	
	public int removeCaller(){
		if  (isEmpty()){
			return -1;
		}
		size--;
		return callerNo[size];
	}

	
}

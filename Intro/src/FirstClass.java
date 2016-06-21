import java.util.Scanner;

public class FirstClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world!"); //this prints
		
		//learning scanners for user input
		Scanner in = new Scanner(System.in);
		
		System.out.println("Type a string (q or Q to quit)");
		
		boolean quit = false;
		while(!quit){
			String str = in.nextLine();
			if(str.equalsIgnoreCase("q")){
				System.out.println("Bye!");
				quit = true;
			} else {
				System.out.println(str);
			}
		}
		
		//learning arrays
		int[] A = {1, 2, 3, 4, 5};
		for(int i = 0; i < A.length; i++){
			System.out.println("A["+i+"]="+A[i]);
		}
		int[] B = A;
		for(int i = 0; i < B.length; i++){ //the scope of i only exists in this one loop
			System.out.println("B["+i+"]="+B[i]);
		}
		//if we were to update the copy of B here, it would also update A
		//this is called a shallow copy!
		//if we want a deep copy we need to do:
		//int[] B = A.clone();
		
		
		//learning constructors
		A a = new A(10);
		System.out.println(a); //if we don't override the toString operator, this will print a's address in memory
		
		in.close();
	}

}

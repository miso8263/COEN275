package interfaces_example;

public class Employee implements Payable {

	
	private final String FirstName;
	private final String LastName;
	private final String SSN;
	private final double Payment;
	
	public Employee(String firstName, String lastName, String ssn, double pay){
		FirstName = firstName;
		LastName = lastName;
		SSN = ssn;
		Payment = pay;
	}
	
	@Override
	public int getPaymentAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

}

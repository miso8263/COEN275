package class1a;
//This is the class activity
//06/20/2016

class Puppy {
	private String name;
	private int age;
	
	public Puppy(){ //default constructor
		name = "Name not given yet";
		age = 0;
	}
	
	public Puppy(String name, int age){ //constructor
		this.name = name;
		this.age = age;
	}
	
	public String getName(){
		return name;
	}
	
	public int getAge(){
		return age;
	}
	
	@Override
	public String toString(){
		return "Puppy named "+name;
	}
}

public class Main {
	public static void main(String[] args){
		Puppy myPuppy = new Puppy("Rosie", 7);
		System.out.println("Puppy name: " + myPuppy.getName());
		System.out.println("Puppy age: " + myPuppy.getAge());
		
		System.out.println(myPuppy);
		
		Cube.jee();
	}

}

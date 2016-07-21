
public class A {
	int i;
	public A(int i){
		this.i = i; //"this" is the current reference; the object we're in
	} //end constructor
	
	@Override
	public String toString(){
		return "i="+i;
	}
} //end class A

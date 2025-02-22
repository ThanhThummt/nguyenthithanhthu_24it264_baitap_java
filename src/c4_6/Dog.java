package c4_6;

public class Dog extends Mammal {

	public Dog(String name) {
		super(name);
	}
	@Override
	public String toString() {
		return "Cat ["+super.toString()+"]";
	}
	public void greets(){
		System.out.println("Woof");
	}
	public void greets(Dog another){
		System.out.println("Wooooof");
	}
}

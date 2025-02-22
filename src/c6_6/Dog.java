package c6_6;

public class Dog extends Animal{

	public Dog(String name) {
		super(name);
	}

	@Override
	public void greets() {
		// TODO Auto-generated method stub
		System.out.println("Woof");
	}
	public void greets(Dog another) {
		System.out.println("Woooof");
	}

}

package c8;

public class Test {


	public static void main(String[] args) {
		MyIntStack s = new MyIntStack(3);

		s.push(10);
		s.push(20);
		s.push(30);

		s.display();

		System.out.println("Pop: " + s.pop());
		System.out.println("Peek: " + s.peek());

		s.push(40);
		s.display();

		System.out.println("Is Full? " + s.isFull());
	}
}

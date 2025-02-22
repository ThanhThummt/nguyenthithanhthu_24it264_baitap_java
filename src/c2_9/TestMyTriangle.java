package c2_9;

public class TestMyTriangle {
	public static void main(String[] args) {

		MyTriangle t1 = new MyTriangle(1, 1, 2, 2, 3, 3);
		System.out.println(t1);
		System.out.println("Type: " + t1.getType());
		System.out.println("Perimeter: " + t1.getPerimeter());

		MyTriangle t2 = new MyTriangle(1, 1, 2, 2, 4, 4);
		System.out.println(t2);
		System.out.println("Type: " + t2.getType());
		System.out.println("Perimeter: " + t2.getPerimeter());

		MyTriangle t3 = new MyTriangle(1, 1, 2, 2, 5, 5);
		System.out.println(t3);
		System.out.println("Type: " + t3.getType());
		System.out.println("Perimeter: " + t3.getPerimeter());

	}
}

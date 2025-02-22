package c4_5;

public class TestShape extends Shape {
	public static void main(String[] args) {
		Shape c1 = new Shape();
		 System.out.println(c1);
		 Shape c2 = new Circle(2, "blue", true);
		 System.out.println(c2);
		
		 Rectangle r1 = new Rectangle(4.0, 6.0, "green", true);
		 System.out.println(r1);
		  System.out.println( r1.getArea());
		  System.out.println( r1.getPerimeter());
	}}

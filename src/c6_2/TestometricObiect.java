package c6_2;

public class TestometricObiect {
public static void main(String[] args) {
		Circle c= new Circle(1);
		 System.out.println( c);
		 System.out.println(Math.round( c.getArea()));
		 System.out.println( c.getPerimeter());
		 
		 Rectangle r = new Rectangle(2, 2);
		 System.out.println( r);
		 System.out.println( r.getArea());
		 System.out.println( r.getPerimeter());
}
}

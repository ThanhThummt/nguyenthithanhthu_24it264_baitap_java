package c4_4;

public class TestMovalePoint {
public static void main(String[] args) {
	Point p1 = new Point();
	System.out.println(p1);
	Point p2 = new Point(1, 2);
	System.out.println(p2);
	
	MovalePoint m1= new MovalePoint();
	System.out.println(m1);
	MovalePoint m2= new MovalePoint(1, 2, 3, 3);
	System.out.println(m2);
	
	System.out.println(m2.move());
	
}
}

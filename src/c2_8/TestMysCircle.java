package c2_8;

public class TestMysCircle {
  public static void main(String[] args) {
	MyPoint p1 = new MyPoint();
	System.out.println(p1);
	
	MyCircle c1 = new MyCircle();
	System.out.println(c1);
	MyCircle c2 = new MyCircle();
	System.out.println(c2);
	
	c1.setCenterXY(1,5);
	System.out.println(c1);
	
	System.out.println(c1.distance(c2));
}
}

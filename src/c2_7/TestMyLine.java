package c2_7;

public class TestMyLine {
public static void main(String[] args) {
	MyPoint p1 = new MyPoint();
	System.out.println(p1);
	MyPoint p2 = new MyPoint(1, 3);
	System.out.println(p2);
	
	//MyLine l1= new MyLine(5, 6);
	MyLine l2 = new MyLine(3, 2, 1, 2);
	System.out.println(l2);
	l2.setBeginX(6);
	System.out.println(l2);
	l2.setEndXY(5,5);
	System.out.println(l2);
	System.out.println(l2.getLength());
	System.out.println(l2.getGradient());
}

}

package c2_6;

public class TestMyPoint {
public static void main(String[] args) {
	MyPoint p1 = new MyPoint();  
	System.out.println(p1);      
	p1.setY(6);
	System.out.println("x is: " + p1.getX());  
	System.out.println("y is: " + p1.getY());
	p1.setXY(3, 0);  
	System.out.println(p1.getXY()[0]);  
	System.out.println(p1.getXY()[1]);
	System.out.println(p1);

	MyPoint p2 = new MyPoint(0, 4);  
	System.out.println(p2);
	
	System.out.println(p1.distance(p2));    
	System.out.println(p2.distance(p1));    
	System.out.println(p1.distance(5, 6)); 
	System.out.println(p1.distance());   
	
	MyPoint[] points = new MyPoint[10];
	for (int i = 0; i < points.length; i++) {
	   points[i] = new MyPoint(i,i);    
	}
	 for (MyPoint point : points) {
		  System.out.println(point);
	}
}
}

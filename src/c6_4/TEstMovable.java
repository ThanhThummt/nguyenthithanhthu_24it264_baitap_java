package c6_4;

public class TEstMovable {
public static void main(String[] args) {
	Movable m1 = new MovablePoint(2, 3, 1, 1);
	 System.out.println(m1);
	 m1.moveUp();
	 System.out.println(m1);
	m1.moveRight();
	 System.out.println(m1);
	 m1.moveDown();
	 System.out.println(m1);
	 m1.moveLeft();
	 System.out.println(m1);
	 
	 MovableCircle c = new MovableCircle(5, 5, 2, 2, 10);
	 System.out.println( c);
    c.moveUp();
     System.out.println(  c);
    c.moveRight();
     System.out.println(c);
    c.moveDown();
     System.out.println(c);
    c.moveLeft();
     System.out.println( c);
}
}
